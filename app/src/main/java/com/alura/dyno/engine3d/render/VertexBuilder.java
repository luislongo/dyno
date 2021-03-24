package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class VertexBuilder {
    private Vector3 position;
    private RGBAColor color;
    private Vector3 normal;
    private Vector2 uvs;

    public VertexBuilder() {
        position = new Vector3(0.0f);
        color = RGBAColor.MAGENTA;
        normal = new Vector3(0.0f);
        uvs = new Vector2(0.0f);
    }
    public VertexBuilder setPosition(Vector3 position) {
        this.position = position;
        return this;
    }
    public VertexBuilder setColor(RGBAColor color) {
        this.color = color;
        return this;
    }
    public VertexBuilder setUVs(Vector2 uvs) {
        this.uvs = uvs;
        return this;
    }
    public VertexBuilder setNormal(Vector3 normal) {
        this.normal = normal;

        return this;
    }
    public Vector3 getPosition() {
        return position;
    }
    public RGBAColor getColor() {
        return color;
    }
    public Vector3 getNormal() {
        return normal;
    }
    public Vector2 getUvs() {
        return uvs;
    }

    public Vertex build() {
        return new Vertex(this);
    }
}
