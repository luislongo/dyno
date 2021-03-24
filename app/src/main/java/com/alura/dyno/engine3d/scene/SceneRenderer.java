package com.alura.dyno.engine3d.scene;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.alura.dyno.engine3d.eventsystem.events.OnRenderEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewChangedEvent;
import com.alura.dyno.engine3d.eventsystem.events.OnViewCreatedEvent;
import com.alura.dyno.engine3d.eventsystem.handlers.OnRenderEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnViewChangedHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnViewCreatedHandler;
import com.alura.dyno.math.graphics.Vector2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SceneRenderer implements GLSurfaceView.Renderer {

    ISceneRendererListener listener;
    Context context;
    Vector2 screenSize;

    public SceneRenderer(Context context) {
        super();
        this.context = context;
    }

    @Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.5f, 0.5f, 0.5f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
        GLES20.glCullFace(GLES20.GL_BACK);;

        if (listener != null) {
            listener.OnViewCreated(new OnViewCreatedEvent());
        }
    }
    @Override public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
                screenSize = new Vector2(width, height);

        if (listener != null) {
            listener.OnViewChanged(new OnViewChangedEvent(width, height));
        }
    }
    @Override public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);

        if (listener != null && screenSize.x() != 0 && screenSize.y() != 0) {
            listener.OnRender(new OnRenderEvent());
        }
    }

    public void setOnRenderListener(ISceneRendererListener listener) {
        this.listener = listener;
    }

}
