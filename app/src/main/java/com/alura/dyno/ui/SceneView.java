package com.alura.dyno.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class SceneView extends GLSurfaceView {
    SceneController controller;
    SceneModel model;

    public SceneView(Context context) {
        super(context);
    }
    public SceneView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setController(SceneController controller) {
        super.setRenderer(controller);
        this.controller = controller;
    }

    public void setModel(SceneModel model) {
        this.model = model;
    }

}







