package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.GraphicMatrix;

public class ProjectionMatrixUniform extends GraphicMatrixUniform {

    public ProjectionMatrixUniform(GraphicMatrix value) {
        super(value);
    }

    @Override
    public GraphicMatrix getValue(Renderer renderer) {
        return SceneController.getModel().getMainCamera().getProjectionMatrix();
    }

    @Override
    public String getName() {
        return "u_ProjectionMatrix";
    }
}
