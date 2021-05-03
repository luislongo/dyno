package com.alura.dyno.engine3d.render.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.math.graphics.Vector3;

public abstract class Vector3Uniform extends Uniform<Vector3> {
    public Vector3Uniform(Vector3 value) {
        super();
    }

    @Override
    public void insertInto(int handle, Renderer renderer)
    {
        Vector3 value = getValue(renderer);
        GLES20.glUniform3f(handle, value.x(), value.y(), value.z());
    }
}
