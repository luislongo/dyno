package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.render.shader.uniforms.Uniform;

import java.util.ArrayList;
import java.util.List;

public class Material {
    List<Uniform> uniforms;

    public Material() {
        uniforms = new ArrayList<>();
    }
    public void addUniform(Uniform uniform) {
        uniforms.add(uniform);
    }
}
