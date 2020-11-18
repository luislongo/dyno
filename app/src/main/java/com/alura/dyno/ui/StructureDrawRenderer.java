package com.alura.dyno.ui;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.components.MeshRenderer;
import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.system.shaders.ShaderBase;
import com.alura.dyno.engine3d.system.shaders.ShaderLoader;
import com.alura.dyno.engine3d.system.shaders.ShaderType;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.system.vertex.Vertex;
import com.alura.dyno.engine3d.system.vertex.VertexBuffer;
import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.engine3d.utils.TriangleFactory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class StructureDrawRenderer implements GLSurfaceView.Renderer, ColorPalette {

    SurfaceRendererListener listener;
    Context context;

    float[] color;
    private int mProgram;
    private MeshBuffer buffer;
    private Shader objShader;
    private MeshRenderer<Shader> renderer;

    public StructureDrawRenderer(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        if (listener != null) {
            listener.OnSurfaceCreated(this);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        if (listener != null) {
            listener.OnSurfaceChanged(width, height);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
        onRender();
    }

    public void onRender() {
        if (listener != null) {
            listener.OnRender(this);
        }
    }

    public void setOnRenderListener(SurfaceRendererListener listener) {
        this.listener = listener;
    }

    public void testRender()
    {
        final int vertexCount = 6;
    }

    public interface SurfaceRendererListener {
        void OnSurfaceCreated(StructureDrawRenderer renderer);

        void OnSurfaceChanged(int width, int height);

        void OnRender(StructureDrawRenderer renderer);
    }
}
