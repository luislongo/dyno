package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.GraphicMatrix;

public abstract class GraphicMatrixUniform extends Uniform<GraphicMatrix> {

    public GraphicMatrixUniform(GraphicMatrix value) {
        super();
    }

    @Override
    public void insertInto(int handle, Renderer renderer) {
        GraphicMatrix value = getValue(renderer);
        GLES20.glUniformMatrix4fv(handle, 1, false, value.toTransposedArray(), 0);

    }
}
