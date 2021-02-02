package com.alura.dyno.engine3d.system.shaders;

import android.opengl.GLES20;
import android.util.Log;

import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.system.shaders.uniforms.Uniform;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.Matrix4F;
import com.alura.dyno.maths.Vector2F;
import com.alura.dyno.maths.Vector3F;
import com.alura.dyno.maths.Vector4F;
import com.alura.dyno.maths.VectorF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Shader {
    HashMap<ShaderType, Integer> shaderHandles;
    HashMap<Uniform, Integer> uniforms;
    private int programHandle;

    public Shader(List<ShaderSource> sources) {
        initializeHandleHashMaps();
        compileShader(sources);
    }
    private void initializeHandleHashMaps() {
        shaderHandles = new HashMap<>();
        uniforms = new HashMap<>();
    }
    private void compileShader(List<ShaderSource> sources) {
        ShaderCompiler compiler = new ShaderCompiler();

        for(ShaderSource source : sources) {
            int handle = compiler.putShader(source);
            shaderHandles.put(source.getType(), handle);
        }

        programHandle = compiler.compile();
    }

    protected final void setUniform1F(Uniform<Float> uniform) {
        int handle = getUniformHandle(uniform);
        float value = uniform.getValue();

        GLES20.glUniform1f(handle, value);
    }
    protected final void setUniformVector2F(Uniform<Vector2F> uniform) {
        int handle = getUniformHandle(uniform);
        Vector2F value = uniform.getValue();

        GLES20.glUniform2f(handle, value.x(), value.y());
    }
    protected final void setUniformVector3F(Uniform<Vector3F> uniform) {
        int handle = getUniformHandle(uniform);
        Vector3F value = uniform.getValue();

        GLES20.glUniform3f(handle, value.x(), value.y(), value.z());
    }
    protected final void setUniformVector4F(Uniform<Vector4F> uniform) {
        int handle = getUniformHandle(uniform);
        Vector4F value = uniform.getValue();

        GLES20.glUniform4f(handle, value.x(), value.y(), value.z(), value.w());
    }
    protected final void setUniformColor(Uniform<RGBAColor> uniform) {
        int handle = getUniformHandle(uniform);
        RGBAColor value = uniform.getValue();

        GLES20.glUniform4f(handle, value.r(), value.g(), value.b(), value.a());
    }
    protected final void setUniformTexture(Uniform<Texture> uniform) {
        int handle = getUniformHandle(uniform);
        Texture value = uniform.getValue();

        GLES20.glUniform1i(handle, value.id());
    }
    protected final void setUniformMat4F(Uniform<Matrix4F> uniform) {
        int handle = getUniformHandle(uniform);
        Matrix4F value = uniform.getValue();

        GLES20.glUniformMatrix4fv(handle, 16, false, value.toArray(), 0);
    }

    public int getUniformHandle(Uniform uniform) {
        if(uniforms.containsKey(uniform)) {
            return uniforms.get(uniform);
        } else
        {
            int handle = GLES20.glGetUniformLocation(programHandle, uniform.getName());

            if(!doesHandleExist(handle)) {
                Log.e("SHADER", "SHADER::UNIFORM::COULD NOT FIND UNIFORM::NAME: " + uniform.getName());
            }

            return handle;
        }
    }
    private boolean doesHandleExist(int handle) {
        return !(handle == -1);
    }

    public abstract void setUniforms();

    public final void use() {
        GLES20.glUseProgram(programHandle);
    }
    public final void unuse() {
        GLES20.glUseProgram(0);
    }
}
