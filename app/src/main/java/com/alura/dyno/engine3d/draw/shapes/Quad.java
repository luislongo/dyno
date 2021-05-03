package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Quaternion;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class Quad extends Shape {
    protected float left, right, bottom, top;
    protected float uvLeft, uvRight, uvBottom, uvTop;
    protected RGBAColor color;

    protected Quad(Builder builder) {
        super();

        this.bottom = builder.bottom; this.top = builder.top;
        this.left = builder.left; this.right = builder.right;
        this.uvLeft = builder.uvLeft; this.uvRight = builder.uvRight;
        this.uvBottom = builder.uvBottom; this.uvTop = builder.uvTop;
        this.color = builder.color;

        invalidate();
    }

    @Override protected void calculateVertices() {
        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(left, bottom, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(uvLeft, uvBottom))
            .setColor(color)
            .build());

        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(right, bottom, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(uvRight,uvBottom))
            .setColor(color)
            .build());

        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(right,top, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(uvRight, uvTop))
            .setColor(color)
            .build());

        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(left, top, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(uvLeft, uvTop))
            .setColor(color)
            .build());
    }
    @Override protected void calculateTriangles() {
        triangles.add(new Triangle(0, 1, 2));
        triangles.add(new Triangle(0, 2, 3));
    }
    @Override protected void calculateLines() {
        lines.add(new Line(0,1));
        lines.add(new Line(1,2));
        lines.add(new Line(2,3));
        lines.add(new Line(3,0));
    }

    public static class Builder {
        protected float left, right, bottom, top;
        protected float uvLeft, uvRight, uvBottom, uvTop;
        protected RGBAColor color;

        public Builder() {};
        public Builder setBounds(float left, float right, float bottom, float top) {
            this.bottom = bottom; this.top = top;
            this.left = left; this.right = right;

            return this;
        }
        public Builder setUVs(float uvLeft, float uvRight, float uvBottom, float uvTop) {
            this.uvLeft = uvLeft; this.uvRight = uvRight;
            this.uvBottom = uvBottom; this.uvTop = uvTop;

            return this;
        }
        public Builder setColor(RGBAColor color) {
            this.color = color;

            return this;
        }
        public Quad build() {
            return new Quad(this);
        }
    }
}
