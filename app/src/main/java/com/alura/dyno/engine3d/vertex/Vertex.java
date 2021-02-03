package com.alura.dyno.engine3d.vertex;

import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.graphics.Vector2F;
import com.alura.dyno.maths.graphics.Vector3F;

public class Vertex {
    public final static int BYTES = 9 * Float.BYTES;
    public Vector3F position;
    public Vector3F normal;
    public RGBAColor color;
    public Vector2F uvs;

    public Vertex(VertexBuilder builder) {
        this.position = builder.position.clone();
        this.normal = builder.normal.clone();
        this.color = builder.color.clone();
        this.uvs = builder.uvs.clone();
    }
    public Vertex transform(VertexTransform vt) {
        VertexBuilder vb = new VertexBuilder();
        vb.setPosition(vt.transformPosition(this.position))
                .setNormal(vt.transformNormal(this.normal))
                .setColor(vt.transformColor(this.color))
                .setUVs(vt.transformUVs(this.uvs));

        return vb.build();
    }


    public static class VertexBuilder {
        private Vector3F position;
        private RGBAColor color;
        private Vector3F normal;
        private Vector2F uvs;

        public VertexBuilder() {
            position = Vector3F.ZERO();
            color = ColorPalette.MAGENTA;
            normal = Vector3F.ZERO();
            uvs = Vector2F.ZERO();
        }

        public VertexBuilder setPosition(Vector3F position) {
            this.position = position;
            return this;
        }
        public VertexBuilder setColor(RGBAColor color) {
            this.color = color;
            return this;
        }
        public VertexBuilder setUVs(Vector2F uvs) {
            this.uvs = uvs;
            return this;
        }
        public VertexBuilder setNormal(Vector3F normal) {
            this.normal = normal;

            return this;
        }

        public Vertex build() {
            return new Vertex(this);
        }

    }
}
