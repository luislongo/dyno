package com.alura.dyno.ui;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import com.alura.dyno.engine3d.eventsystem.TreeEventDispatcher;
import com.alura.dyno.engine3d.eventsystem.handlers.OnCreateEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnRenderEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnScreenSizeChangedEventHandler;
import com.alura.dyno.engine3d.eventsystem.handlers.OnUpdateEventHandler;
import com.alura.dyno.engine3d.input.InputDetector;
import com.alura.dyno.math.graphics.Vector2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SceneController implements GLSurfaceView.Renderer, View.OnTouchListener {
    SceneView view;
    SceneModel model;
    TreeEventDispatcher dispatcher;
    InputDetector detector;

    public void SceneController(SceneView view, SceneModel model) {
        this.view = view;
        this.model = model;

        view.setRenderer(this);
        view.setOnTouchListener(this);
    }

    @Override public boolean onTouch(View v, MotionEvent event) {
        detector.notifyMotionEvent(event);
        return true;
    }
    @Override public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        dispatcher.sendDownTheTree(new OnCreateEventHandler.OnCreateEvent());
    }
    @Override public void onSurfaceChanged(GL10 gl, int width, int height) {
        dispatcher.sendDownTheTree(new OnScreenSizeChangedEventHandler
                .OnScreenSizeChangedEvent(new Vector2(width,height)));
    }
    @Override public void onDrawFrame(GL10 gl) {
        dispatcher.sendDownTheTree(new OnUpdateEventHandler.OnUpdateEvent());
        dispatcher.sendDownTheTree(new OnRenderEventHandler.OnRenderEvent());
    }
}
