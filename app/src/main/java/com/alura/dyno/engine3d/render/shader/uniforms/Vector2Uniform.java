package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.math.graphics.Vector2;

public abstract class Vector2Uniform extends Uniform<Vector2> {

    public Vector2Uniform(Vector2 value) {
        super(value);
    }

    @Override
    public void insertInto(int handle) {
        GLES20.glUniform2f(handle, value.x(), value.y());
    }
}
