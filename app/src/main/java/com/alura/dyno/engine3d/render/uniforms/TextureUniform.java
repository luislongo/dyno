package com.alura.dyno.engine3d.render.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.script.Renderer;

public abstract class TextureUniform extends Uniform<Texture> {

    public TextureUniform() {
        super();
    }
    @Override public void insertInto(int handle, Renderer renderer) {
        Texture value = getValue(renderer);
        GLES20.glUniform1i(handle, value.getSlot());
    }
}
