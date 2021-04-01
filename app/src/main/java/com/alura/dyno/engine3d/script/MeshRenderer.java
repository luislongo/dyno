package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.Material;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.scene.SceneController;

public class MeshRenderer extends Renderer<Mesh> {
    private Material material;

    public MeshRenderer(String name) {
        super(name);
    }

    @Override public void setUniforms() {
        shader.setModelMatrix(getParent().transform().getModelMatrix());
        shader.setViewMatrix(SceneController.getModel().getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneController.getModel().getMainCamera().getProjectionMatrix());
    }
    @Override public int getDrawMode() {
        return GLES20.GL_TRIANGLES;
    }

}
