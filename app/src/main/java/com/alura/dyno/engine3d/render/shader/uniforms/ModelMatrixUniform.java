package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.GraphicMatrix;

public class ModelMatrixUniform extends GraphicMatrixUniform {

    public ModelMatrixUniform() {
        super();
    }

    @Override
    public GraphicMatrix getValue(Renderer renderer) {
        return renderer.getParent().getTransform().getModelMatrix();
    }
    @Override
    public String getName() {
        return "u_ModelMatrix";
    }
}
