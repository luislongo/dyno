package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

public class Circle extends Shape {
    private float radius;
    private int sides;

    public Circle(float radius, int sides) {
        super();

        this.radius = radius;
        this.sides = sides;
        invalidate();
    }
    @Override protected void calculateVertices() {
        Vector3 normal = new Vector3(0.0f, 0.0f, 1.0f);

        for(int i = 0; i < sides; i++) {
            double angle = 2.0f * (float) Math.PI * i / sides;
            Vector3 position = new Vector3(
                    radius * (float) Math.cos(angle),
                    radius * (float) Math.sin(angle),
                    0);
            Vector2 uvs = new Vector2(position).divide(radius).plus(new Vector2(1.0f))
                    .divide(2.0f);

            vertices.add(new VertexBuilder()
                .setPosition(position)
                .setNormal(normal.clone())
                .setUVs(new Vector2(position))
                .setColor(RGBAColor.WHITE)
            .build());
        }
        vertices.add(new VertexBuilder()
                .setPosition(new Vector3(0.0f, 0.0f, 0.0f))
                .setNormal(normal.clone())
                .setUVs(new Vector2(0.0f))
                .setColor(RGBAColor.WHITE)
                .build());
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
