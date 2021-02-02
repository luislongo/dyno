package com.alura.dyno.engine3d.system.shaders.uniforms;

import androidx.annotation.Nullable;

import com.alura.dyno.engine3d.system.shaders.Shader;

public class Uniform<T> {
    T value;
    String name;

    public Uniform(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return this.value;
    }
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
