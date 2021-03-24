package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.draw.DrawOptions;
import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
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

    @Override protected void calculatePositions() {
        positions.add(new Vector3(-halfWidth, -halfHeight, 0.0f));
        positions.add(new Vector3( halfWidth, -halfHeight, 0.0f));
        positions.add(new Vector3( halfWidth,  halfHeight, 0.0f));
        positions.add(new Vector3(-halfWidth,  halfHeight, 0.0f));
    }
    @Override protected void calculateNormals() {
        normals.add(new Vector3(0.0f, 0.0f, 1.0f));
        normals.add(new Vector3(0.0f, 0.0f, 1.0f));
        normals.add(new Vector3(0.0f, 0.0f, 1.0f));
        normals.add(new Vector3(0.0f, 0.0f, 1.0f));
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
