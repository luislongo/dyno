package com.alura.dyno.engine3d.render.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.engine3d.utils.RGBAColor;

public abstract class ColorUniform extends Uniform<RGBAColor> {

    public ColorUniform() {
        super();
    }

    @Override
    public void insertInto(int handle, Renderer renderer) {
        RGBAColor value = getValue(renderer);
        GLES20.glUniform4f(handle, value.r(), value.g(), value.b(), value.a());
    }
}
