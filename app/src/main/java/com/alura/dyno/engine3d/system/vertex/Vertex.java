package com.alura.dyno.engine3d.system.vertex;

import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.Vector2G;
import com.alura.dyno.maths.Vector3G;

public class Vertex {
    public final static int BYTES = 9 * Float.BYTES;
    public Vector3G position;
    public RGBAColor color;
    public Vector2G uv;

    public Vertex(VertexBuilder builder) {
        this.position = new Vector3G(builder.x, builder.y, builder.z);
        this.color = new RGBAColor(builder.r, builder.g, builder.b, builder.a);
        this.uv = new Vector2G(builder.uv1, builder.uv2);
    }

    public float[] data() {
        return new float[]{position.x(), position.y(), position.z(),
                color.r, color.g, color.b, color.a,
                uv.x(), uv.y()};
    }

    public static class VertexBuilder {
        float x, y, z;
        float r, g, b, a;
        float uv1, uv2;

        public VertexBuilder(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public VertexBuilder setColor(float r, float g, float b, float a) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = a;

            return this;
        }

        public VertexBuilder setColor(RGBAColor color) {
            return setColor(color.r, color.g, color.b, color.a);
        }

        public VertexBuilder setUVs(float uv1, float uv2) {
            this.uv1 = uv1;
            this.uv2 = uv2;

            return this;
        }

        public Vertex build() {
            return new Vertex(this);
        }

    }


}
