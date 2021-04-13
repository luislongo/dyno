package com.alura.dyno.engine3d.render.shader.uniforms;

import com.alura.dyno.engine3d.render.shader.Shader;

public abstract class Uniform<T> {
            T value;
            int handle;

        public Uniform(T value) {
            this.value = value;
        }

        public void setValue(T value) {
            this.value = value;
        }
        public T getValue() {
            return this.value;
        }
        public void setHandle(int handle) {
            this.handle = handle;
        }
        public int getHandle() {
            return this.handle;
        }
        public abstract void insertInto();

    }

