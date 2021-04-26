package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.Texture;

public class UniformTexture extends Uniform<Texture> {
    public UniformTexture(Texture value) {
        super(value);
    }

    @Override public void insertInto() {
        GLES20.glUniform1i(handle, value.getSlot());
    }
    @Override public void bind() {
        value.bind();
    }
    @Override public void unbind() {
        value.unbind();
    }
}
