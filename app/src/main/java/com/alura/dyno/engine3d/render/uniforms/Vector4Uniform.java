package com.alura.dyno.engine3d.render.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.Vector4;

public abstract class Vector4Uniform extends Uniform<Vector4> {
    public Vector4Uniform(Vector4 value) {
        super();
    }

    @Override
    public void insertInto(int handle, Renderer renderer) {
        Vector4 value = getValue(renderer);
        GLES20.glUniform4f(handle, value.x(), value.y(), value.z(), value.w());
    }
}
