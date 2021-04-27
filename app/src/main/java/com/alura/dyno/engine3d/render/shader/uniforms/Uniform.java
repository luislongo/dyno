package com.alura.dyno.engine3d.render.shader.uniforms;

import android.opengl.GLES20;
import android.util.Log;

import com.alura.dyno.engine3d.render.shader.Shader;

public abstract class Uniform<T> {
        protected T value;

        public Uniform(T value) {
            this.value = value;
        }

        public void setValue(T value) {
            this.value = value;
        }
        public T getValue() {
            return this.value;
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

        public abstract void insertInto(int handle);
        public abstract String getName();

        public void bind() {}
        public void unbind() {}
    }

