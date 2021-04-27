package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;
import android.util.Log;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.engine3d.script.Renderer;

public abstract class Uniform<T> {
    public Uniform() {
        }

    public int getHandleFromShader(Shader shader) {
            int handle = GLES20.glGetUniformLocation(shader.getProgramId(), getName());

            if(!doesHandleExist(handle)) {
                Log.e("SHADER", "SHADER::UNIFORM::COULD NOT FIND UNIFORM::NAME: " + getName());
            }

            return handle;
        }
    private boolean doesHandleExist(int handle) {
        return !(handle == -1);
    }

    public abstract T getValue(Renderer renderer);
    public abstract void insertInto(int handle, Renderer renderer);

    public abstract String getName();

}

