package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnRenderEventHandler;
import com.alura.dyno.engine3d.render.IFaceAbstraction;
import com.alura.dyno.engine3d.render.Material;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.buffer.GraphicObjectData;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.engine3d.render.shader.SimpleShader;
import com.alura.dyno.math.MathExtra;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public abstract class Renderer<T extends GraphicObjectData> extends Script {
    protected T sharedData;
    protected Material material;
    protected Shader shader;
    protected boolean isLoaded = false;

    private FloatBuffer vbo;
    private IntBuffer ibo;

    public Renderer(String name) {
        super(name);
        addEventHandler(new OnRender());
        isLoaded = false;
    }
    public abstract int getDrawMode();

    public void setMaterial(Material material) {
        this.material = material;
    }
    public void setShader(Shader shader) {
        this.shader = shader;
    }
    protected void setData(T data) {
        this.sharedData = data;
        isLoaded = false;
    }
    public Material getMaterial() {
        return material;
    }
    public Shader getShader() {
        return shader;
    }
    public FloatBuffer getVBO() {
        return vbo;
    }

    public void invalidate() {
        clearBufferData();
        updateBufferData();
        isLoaded = true;
    }
    private void clearBufferData() {
        if(vbo != null) {
            vbo.clear();
        }
        if(ibo != null) {
            ibo.clear();
        }
    }
    private void updateBufferData() {
        allocateVBO();
        allocateIBO();
    }
    private void allocateVBO() {
        BufferLayout layout = shader.getLayout();

        vbo = ByteBuffer.allocateDirect(sharedData.getVertexCount() * layout.getLayoutSize())
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        List<Vertex> vertices = sharedData.getVertices();
        for(Vertex v : vertices) {
            layout.layoutVertex(v, vbo);
        }
    }
    private void allocateIBO() {
        ibo = ByteBuffer.allocateDirect(3 * sharedData.getFaceCount() * Integer.BYTES)
                .order(ByteOrder.nativeOrder()).asIntBuffer();

        List<IFaceAbstraction> faces = sharedData.getFaces();
        for(IFaceAbstraction f : faces) {
            ibo.put(f.getIndices());
        }

        ibo.position(0);
    }

    private class OnRender extends OnRenderEventHandler {

        @Override public void onExecute(OnRenderEvent event) {
            if(!isLoaded) {
                invalidate();
            }

            shader.use();
            shader.bind(Renderer.this);
            shader.setUniformsFromRenderer(Renderer.this);


            GLES20.glDrawElements(getDrawMode(),
                    3 * sharedData.getFaceCount(), GLES20.GL_UNSIGNED_INT, ibo);
        }
    }
}
