package com.alura.dyno.engine3d.render.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.script.Renderer;

public abstract class FloatUniform1 extends Uniform<Float> {

    public FloatUniform1() {
        super();
    }

    @Override
    public void insertInto(int handle, Renderer renderer) {
        float value = getValue(renderer);
        GLES20.glUniform1f(handle, value);
    }
}
