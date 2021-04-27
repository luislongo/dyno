package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.math.graphics.Vector3;

public abstract class Vector3Uniform extends Uniform<Vector3> {
    public Vector3Uniform(Vector3 value) {
        super(value);
    }

    @Override
    public void insertInto(int handle)
    {
        GLES20.glUniform3f(handle, value.x(), value.y(), value.z());
    }
}
