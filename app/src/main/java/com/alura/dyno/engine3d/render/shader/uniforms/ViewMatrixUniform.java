package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.GraphicMatrix;

public class ViewMatrixUniform extends GraphicMatrixUniform {
    public ViewMatrixUniform(GraphicMatrix value) {
        super(value);
    }

    @Override
    public GraphicMatrix getValue(Renderer renderer) {
        return SceneController.getModel().getMainCamera().getViewMatrix();
    }
    @Override public String getName() {
        return "u_ViewMatrix";
    }
}
