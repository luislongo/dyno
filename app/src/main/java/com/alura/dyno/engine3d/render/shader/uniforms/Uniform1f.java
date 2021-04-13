package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;

public class Uniform1f extends Uniform<Float> {

    public Uniform1f(Float value) {
        super(value);
    }

    @Override
    public void insertInto() {
        GLES20.glUniform1f(handle, value);
    }
}
