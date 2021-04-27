package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.math.graphics.GraphicMatrix;

public class ProjectionMatrixUniform extends GraphicMatrixUniform {

    public ProjectionMatrixUniform(GraphicMatrix value) {
        super(value);
    }

    @Override
    public String getName() {
        return "u_ProjectionMatrix";
    }
}
