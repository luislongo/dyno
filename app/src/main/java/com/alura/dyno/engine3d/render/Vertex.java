package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class Vertex {
    public final static int BYTES = 9 * Float.BYTES;
    public Vector3 position;
    public Vector3 normal;
    public RGBAColor color;
    public Vector2 uvs;

    public Vertex(VertexBuilder builder) {
        this.position = builder.getPosition();
        this.normal = builder.getNormal();
        this.color = builder.getColor();
        this.uvs = builder.getUvs();
    }
}
