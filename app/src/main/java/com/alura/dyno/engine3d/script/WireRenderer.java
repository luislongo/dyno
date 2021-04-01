package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.buffer.Wire;
import com.alura.dyno.engine3d.scene.SceneController;

public class WireRenderer extends Renderer<Wire> {

    public WireRenderer(String name) {
        super(name);
    }

    @Override public void setUniforms() {
        shader.setModelMatrix(getParent().transform().getModelMatrix());
        shader.setViewMatrix(SceneController.getModel().getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneController.getModel().getMainCamera().getProjectionMatrix());
    }
    @Override public int getDrawMode() {
        return GLES20.GL_LINES;
    }
}
