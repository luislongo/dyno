package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.math.graphics.Vector3;

public class Cube extends Shape {
    float halfWidth;
    float halfHeight;
    float halfDepth;

    public Cube(float width, float height, float depth) {
       super();

       this.halfWidth = width / 2.0f;
       this.halfHeight = height / 2.0f;
       this.halfDepth = depth / 2.0f;

       invalidate();
    }

    @Override protected void calculatePositions() {
        //Back
        positions.add(new Vector3( halfWidth, -halfHeight, -halfDepth));
        positions.add(new Vector3(-halfWidth, -halfHeight, -halfDepth));
        positions.add(new Vector3(-halfWidth,  halfHeight, -halfDepth));
        positions.add(new Vector3( halfWidth,  halfHeight, -halfDepth));
        //Front
        positions.add(new Vector3(-halfWidth, -halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth, -halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth,  halfHeight,  halfDepth));
        positions.add(new Vector3(-halfWidth,  halfHeight,  halfDepth));
        //Left
        positions.add(new Vector3(-halfWidth, -halfHeight, -halfDepth));
        positions.add(new Vector3(-halfWidth, -halfHeight,  halfDepth));
        positions.add(new Vector3(-halfWidth,  halfHeight,  halfDepth));
        positions.add(new Vector3(-halfWidth,  halfHeight, -halfDepth));
        //Right
        positions.add(new Vector3( halfWidth, -halfHeight, -halfDepth));
        positions.add(new Vector3( halfWidth,  halfHeight, -halfDepth));
        positions.add(new Vector3( halfWidth,  halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth, -halfHeight,  halfDepth));
        //Bottom
        positions.add(new Vector3(-halfWidth, -halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth, -halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth, -halfHeight, -halfDepth));
        positions.add(new Vector3(-halfWidth, -halfHeight, -halfDepth));
        //Top
        positions.add(new Vector3(-halfWidth,  halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth,  halfHeight,  halfDepth));
        positions.add(new Vector3( halfWidth,  halfHeight, -halfDepth));
        positions.add(new Vector3(-halfWidth,  halfHeight, -halfDepth));
    }
    @Override protected void calculateNormals() {
        //Back
        normals.add(new Vector3( 0.0f,  0.0f, -1.0f));
        normals.add(new Vector3( 0.0f,  0.0f, -1.0f));
        normals.add(new Vector3( 0.0f,  0.0f, -1.0f));
        normals.add(new Vector3( 0.0f,  0.0f, -1.0f));
        //Front
        normals.add(new Vector3( 0.0f,  0.0f,  1.0f));
        normals.add(new Vector3( 0.0f,  0.0f,  1.0f));
        normals.add(new Vector3( 0.0f,  0.0f,  1.0f));
        normals.add(new Vector3( 0.0f,  0.0f,  1.0f));
        //Left
        normals.add(new Vector3( -1.0f,  0.0f,  0.0f));
        normals.add(new Vector3( -1.0f,  0.0f,  0.0f));
        normals.add(new Vector3( -1.0f,  0.0f,  0.0f));
        normals.add(new Vector3( -1.0f,  0.0f,  0.0f));
        //Right
        normals.add(new Vector3( 1.0f,  0.0f,  0.0f));
        normals.add(new Vector3( 1.0f,  0.0f,  0.0f));
        normals.add(new Vector3( 1.0f,  0.0f,  0.0f));
        normals.add(new Vector3( 1.0f,  0.0f,  0.0f));
        //Bottom
        normals.add(new Vector3( 0.0f,  -1.0f,  0.0f));
        normals.add(new Vector3( 0.0f,  -1.0f,  0.0f));
        normals.add(new Vector3( 0.0f,  -1.0f,  0.0f));
        normals.add(new Vector3( 0.0f,  -1.0f,  0.0f));
        //Top
        normals.add(new Vector3( 0.0f,   1.0f,  0.0f));
        normals.add(new Vector3( 0.0f,   1.0f,  0.0f));
        normals.add(new Vector3( 0.0f,   1.0f,  0.0f));
        normals.add(new Vector3( 0.0f,   1.0f,  0.0f));
    }
    @Override protected void calculateTriangles() {
        //Back
        triangles.add(new Triangle(0, 1, 2));
        triangles.add(new Triangle(0, 2, 3));
        //Front
        triangles.add(new Triangle(4, 5, 6));
        triangles.add(new Triangle(4, 6, 7));
        //Left
        triangles.add(new Triangle(8,  9,10));
        triangles.add(new Triangle(8, 10,11));
        //Right
        triangles.add(new Triangle(12, 13, 14));
        triangles.add(new Triangle(12, 14, 15));
        //Bottom
        triangles.add(new Triangle(16, 17, 18));
        triangles.add(new Triangle(16, 18, 19));
        //Top
        triangles.add(new Triangle(20, 21, 22));
        triangles.add(new Triangle(20, 22, 23));
    }
    @Override protected void calculateLines() {
        //Back
        lines.add(new Line(0, 1));
        lines.add(new Line(1, 2));
        lines.add(new Line(2, 3));
        lines.add(new Line(3, 0));
        //Front
        lines.add(new Line(4, 5));
        lines.add(new Line(5, 6));
        lines.add(new Line(6, 7));
        lines.add(new Line(7, 4));
        //BackToFront
        lines.add(new Line(0,5));
        lines.add(new Line(1,4));
        lines.add(new Line(2,7));
        lines.add(new Line(3,6));
    }
}
