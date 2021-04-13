package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.math.graphics.Vector2;

public class UniformVector2 extends Uniform<Vector2> {

    public UniformVector2( Vector2 value) {
        super(value);
    }

    @Override
    public void insertInto() {
        GLES20.glUniform2f(handle, value.x(), value.y());
    }
}
