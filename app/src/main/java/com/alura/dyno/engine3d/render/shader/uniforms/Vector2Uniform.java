package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.Vector2;

public abstract class Vector2Uniform extends Uniform<Vector2> {

    public Vector2Uniform() {
        super();
    }

    @Override
    public void insertInto(int handle, Renderer renderer) {
        Vector2 value = getValue(renderer);
        GLES20.glUniform2f(handle, value.x(), value.y());
    }
}
