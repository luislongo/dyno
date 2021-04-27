package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.math.graphics.GraphicMatrix;

public class ViewMatrixUniform extends GraphicMatrixUniform {
    public ViewMatrixUniform(GraphicMatrix value) {
        super(value);
    }

    @Override
    public String getName() {
        return "u_ViewMatrix";
    }
}
