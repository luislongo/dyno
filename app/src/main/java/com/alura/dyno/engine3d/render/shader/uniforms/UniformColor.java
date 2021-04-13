package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class UniformColor extends Uniform<RGBAColor> {

    public UniformColor(RGBAColor value) {
        super(value);
    }

    @Override
    public void insertInto() {
        GLES20.glUniform4f(handle, value.r(), value.g(), value.b(), value.a());
    }
}
