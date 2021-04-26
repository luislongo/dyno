package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class Quad extends Shape {
    protected float halfWidth;
    protected float halfHeight;

    public Quad(float width, float height) {
        super();

        this.halfWidth = 0.5f * width;
        this.halfHeight = 0.5f * height;

        invalidate();
    }

    @Override protected void calculateVertices() {
        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(-halfWidth, -halfHeight, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(0.0f, 0.0f))
            .build());

        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(halfWidth, -halfHeight, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(1.0f, 0.0f))
            .build());

        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(halfWidth, halfHeight, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(1.0f, 1.0f))
            .build());

        vertices.add(new VertexBuilder()
            .setPosition(new Vector3(-halfWidth,  halfHeight, 0.0f))
            .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
            .setUVs(new Vector2(0.0f, 1.0f))
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
}
