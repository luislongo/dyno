package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;

public abstract class FloatUniform1 extends Uniform<Float> {

    public FloatUniform1(Float value) {
        super(value);
    }

    @Override
    public void insertInto(int handle) {
        GLES20.glUniform1f(handle, value);
    }
}
