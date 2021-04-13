package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.math.graphics.Vector4;

public class UniformVector4 extends Uniform<Vector4> {
    public UniformVector4(Vector4 value) {
        super(value);
    }

    @Override
    public void insertInto() {
        GLES20.glUniform4f(handle, value.x(), value.y(), value.z(), value.w());
    }
}
