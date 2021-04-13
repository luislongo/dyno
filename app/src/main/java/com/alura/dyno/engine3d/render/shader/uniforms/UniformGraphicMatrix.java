package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.math.graphics.GraphicMatrix;

public class UniformGraphicMatrix extends Uniform<GraphicMatrix> {

    public UniformGraphicMatrix(GraphicMatrix value) {
        super(value);
    }

    @Override
    public void insertInto() {
        GLES20.glUniformMatrix4fv(handle, 1, false, value.toTransposedArray(), 0);

    }
}
