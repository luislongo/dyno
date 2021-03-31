package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnRenderEventHandler;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.render.Material;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.render.shader.SimpleShader;
import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.linalg.Algebra;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MeshRenderer extends Script {
    private Mesh sharedMesh;
    private boolean isLoaded = false;

    private FloatBuffer vbo;
    private IntBuffer ibo;
    private static SimpleShader shader;
    private BufferLayout layout;
    private Material material;

    public MeshRenderer(String name) {
        super(name);
        addEventHandler(new OnRender());
        isLoaded = false;
    }

    public void setMesh(Mesh mesh) {
        this.sharedMesh = mesh;
        isLoaded = false;
    }

    public void setShader(SimpleShader shader) {
        this.shader = shader;
        this.layout = shader.getLayout();


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
        vbo = ByteBuffer.allocateDirect(sharedMesh.getVertexCount() * layout.getLayoutSize())
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        for(Vertex v : sharedMesh.getVertices()) {
            layout.layoutVertex(v, vbo);
        }
    }
    public void allocateIBO() {
        ibo = ByteBuffer.allocateDirect(3 * sharedMesh.getFaceCount() * Integer.BYTES)
                .order(ByteOrder.nativeOrder()).asIntBuffer();

        for(Triangle t : sharedMesh.getFaces()) {
            ibo.put(t.getIndices());
        }
        ibo.position(0);
    }

    private class OnRender extends OnRenderEventHandler {

        @Override public void onExecute(OnRenderEvent event) {
            if(!isLoaded) {
                invalidate();
            }

            shader.setModelMatrix(getParent().transform().getModelMatrix());
            shader.setViewMatrix(SceneController.getModel().getMainCamera().getViewMatrix());
            shader.setProjectionMatrix(SceneController.getModel().getMainCamera().getProjectionMatrix());

            shader.use();
            shader.getLayout().bind(vbo, shader.getProgramId());

            GLES20.glDrawElements(GLES20.GL_TRIANGLES,3 * sharedMesh.getFaceCount(), GLES20.GL_UNSIGNED_INT, ibo);
        }
    }

}
