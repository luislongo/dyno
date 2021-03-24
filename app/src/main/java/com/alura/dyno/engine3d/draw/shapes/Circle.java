package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.draw.DrawOptions;
import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

public class Circle extends Shape {
    private float radius;
    private int sides;

    public Circle(float radius, int sides) {
        super();

        this.radius = radius;
        this.sides = sides;
        invalidate();
    }

    @Override protected void calculatePositions() {
        for(int i = 0; i < sides; i++) {
            double angle = 2.0f * (float) Math.PI * i / sides;
            Vector3 position = new Vector3(
                    radius * (float) Math.cos(angle),
                    radius * (float) Math.sin(angle),
                    0);
           positions.add(position);
        }
        positions.add(new Vector3(0.0f, 0.0f, 0.0f));
    }
    @Override protected void calculateNormals() {
        Vector3 normal = new Vector3(0.0f, 0.0f, 1.0f);
        for(int i = 0; i < sides + 1; i++) {
            normals.add(normal.clone());
        }
    }
    @Override protected void calculateTriangles() {
        for(int i = 0; i < sides ; i++) {
            triangles.add(new Triangle(i, i+1, sides));
        }
        triangles.add(new Triangle(sides-1, 0, sides));
    }
    @Override protected void calculateLines() {
        for(int i = 0; i < sides - 1; i++) {
            lines.add(new Line(i, i+1));
        }
    }
}
