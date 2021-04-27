package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;
import com.alura.dyno.engine3d.utils.RGBAColor;

public abstract class ColorUniform extends Uniform<RGBAColor> {

    public ColorUniform(RGBAColor value) {
        super(value);
    }

    @Override
    public void insertInto(int handle) {
        GLES20.glUniform4f(handle, value.r(), value.g(), value.b(), value.a());
    }
}
