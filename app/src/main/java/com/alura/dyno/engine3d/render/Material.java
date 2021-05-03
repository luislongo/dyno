package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class Material<T extends Renderer> {
    String name;
    Texture albedo = null;
    RGBAColor albedoColor = RGBAColor.MAGENTA;

    Texture metallic = null;
    float smoothFactor = 0.0f;
    float metallicFactor = 1.0f;

    Texture normalMap = null;
    Texture heightMap = null;
    Texture occlusion = null;

    public Material(String name)
        {this.name = name;}

    public Texture getAlbedo() {
        return albedo;
    }
    public RGBAColor getAlbedoColor() {return albedoColor; }
    public Texture getMetallic() {
        return metallic;
    }
    public float getSmoothFactor() {
        return smoothFactor;
    }
    public float getMetallicFactor() {
        return metallicFactor;
    }
    public Texture getNormalMap() {
        return normalMap;
    }
    public Texture getHeightMap() {
        return heightMap;
    }
    public Texture getOcclusion() {
        return occlusion;
    }

    public void setAlbedo(Texture albedo) {
        this.albedo = albedo;
    }
    public void setAlbedoColor(RGBAColor color) {
        this.albedoColor = color;
    }
    public void setMetallic(Texture metallic) {
        this.metallic = metallic;
    }
    public void setSmoothFactor(float smoothFactor) {
        this.smoothFactor = smoothFactor;
    }
    public void setMetallicFactor(float metallicFactor) {
        this.metallicFactor = metallicFactor;
    }
    public void setNormalMap(Texture normalMap) {
        this.normalMap = normalMap;
    }
    public void setHeightMap(Texture heightMap) {
        this.heightMap = heightMap;
    }
    public void setOcclusion(Texture occlusion) {
        this.occlusion = occlusion;
    }

    public void bind() {
        albedo.bind();
    }
    public void unbind() {
        albedo.unbind();
    }

    public String getName() {
        return name;
    }
}
