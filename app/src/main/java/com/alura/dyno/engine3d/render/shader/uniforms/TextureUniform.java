package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.Texture;

public abstract class TextureUniform extends Uniform<Texture> {

    public TextureUniform(Texture value) {
        super(value);
    }
    @Override public void insertInto(int handle) {
        GLES20.glUniform1i(handle, value.getSlot());
    }
}
