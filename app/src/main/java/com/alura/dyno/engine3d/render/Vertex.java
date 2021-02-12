package com.alura.dyno.engine3d.render;

import com.alura.dyno.engine3d.utils.ColorPalette;
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
        this.position = builder.position.clone();
        this.normal = builder.normal.clone();
        this.color = builder.color.clone();
        this.uvs = builder.uvs.clone();
    }

    public static class VertexBuilder {
        private Vector3 position;
        private RGBAColor color;
        private Vector3 normal;
        private Vector2 uvs;

        public VertexBuilder() {
            position = new Vector3(0.0f);
            color = ColorPalette.MAGENTA;
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

        public Vertex build() {
            return new Vertex(this);
        }

    }
}
