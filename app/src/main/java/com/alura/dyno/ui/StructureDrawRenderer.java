package com.alura.dyno.ui;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class StructureDrawRenderer implements GLSurfaceView.Renderer {

    SurfaceRendererListener listener;
    Context context;

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

    public interface SurfaceRendererListener {
        void OnSurfaceCreated(StructureDrawRenderer renderer);

        void OnSurfaceChanged(int width, int height);

        void OnRender(StructureDrawRenderer renderer);
    }
}
