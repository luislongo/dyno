package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.math.graphics.GraphicMatrix;

public class ModelMatrixUniform extends GraphicMatrixUniform {

    public ModelMatrixUniform(GraphicMatrix value) {
        super(value);
    }

    @Override
    public String getName() {
        return "u_ModelMatrix";
    }
}
