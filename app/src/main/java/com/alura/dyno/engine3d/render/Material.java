package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.engine3d.utils.RGBAColor;

public abstract class Material {
    Texture albedo;
    RGBAColor color;

    public Material() {
    }

    public Texture getAlbedo() {
        return albedo;
    }
    public RGBAColor getColor() {
        return color;
    }
    public void setAlbedo(Texture albedo) {
        this.albedo = albedo;
    }
    public void setColor(RGBAColor color) {
        this.color = color;
    }

    public void bind() {
        albedo.bind();
    }
}
