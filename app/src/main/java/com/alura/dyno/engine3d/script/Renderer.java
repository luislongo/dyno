package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnRenderEventHandler;
import com.alura.dyno.engine3d.render.IFaceAbstraction;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.buffer.GraphicObjectData;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.shader.SimpleShader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

public abstract class Renderer<T extends GraphicObjectData> extends Script {
    protected T sharedData;
    private FloatBuffer vbo;
    private IntBuffer ibo;

    protected SimpleShader shader;
    protected boolean isLoaded = false;

    public Renderer(String name) {
        super(name);
        addEventHandler(new OnRender());
        isLoaded = false;
    }

    public void setShader(SimpleShader shader) {
        this.shader = shader;

        isLoaded = false;
    }
    public void setData(T data) {
        this.sharedData = data;
        isLoaded = false;
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
    public void allocateVBO() {
        BufferLayout layout = shader.getLayout();

        vbo = ByteBuffer.allocateDirect(sharedData.getVertexCount() * layout.getLayoutSize())
                .order(ByteOrder.nativeOrder()).asFloatBuffer();

        List<Vertex> vertices = sharedData.getVertices();
        for(Vertex v : vertices) {
            layout.layoutVertex(v, vbo);
        }
    }
    public void allocateIBO() {
        ibo = ByteBuffer.allocateDirect(3 * sharedData.getFaceCount() * Integer.BYTES)
                .order(ByteOrder.nativeOrder()).asIntBuffer();

        List<IFaceAbstraction> faces = sharedData.getFaces();
        for(IFaceAbstraction f : faces) {
            ibo.put(f.getIndices());
        }

        ibo.position(0);
    }

    public abstract void setUniforms();
    public abstract int getDrawMode();

    private class OnRender extends OnRenderEventHandler {

        @Override public void onExecute(OnRenderEvent event) {
            if(!isLoaded) {
                invalidate();
            }
            setUniforms();
            shader.use();
            shader.getLayout().bind(vbo, shader.getProgramId());

            GLES20.glDrawElements(getDrawMode(), 3 * sharedData.getFaceCount(), GLES20.GL_UNSIGNED_INT, ibo);
        }
    }
}
