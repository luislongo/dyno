package com.alura.dyno.engine3d.draw;

import com.alura.dyno.engine3d.draw.shapes.Shape;
import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.render.buffer.Wire;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.linalg.Algebra;

import java.util.ArrayList;
import java.util.List;

    public class ShapeDrawer {
    List<Vertex> vertices;
    List<Triangle> triangles;
    List<Line> lines;
    
    public Cursor cursor;
    GraphicMatrix preMatrix;
    public ShapeDrawer() {
       vertices = new ArrayList<>();
       triangles = new ArrayList<>();
       lines = new ArrayList<>();
    
       cursor = new Cursor();
       preMatrix = Algebra.graphicMatrixBuilder().identity();
    }

    public ShapeDrawer addShape(Shape shape) {
       int curVertexCount = vertices.size();

       GraphicMatrix cursorMatrix = cursor.getMatrix();
       for(Vertex vertex : shape.getVertices()) {
           Vector3 newPosition = vertex.position.clone().multiply(cursorMatrix, 1.0f);
           Vector3 newNormal = vertex.normal.clone().multiply(cursorMatrix, 0.0f);

           Vertex newVertex = new VertexBuilder()
                   .setPosition(newPosition)
                   .setNormal(newNormal)
                   .setUVs(vertex.uvs)
                   .setColor(vertex.color)
                   .build();

           vertices.add(newVertex);
       }

       for(Triangle t : shape.getTriangles()) {
           triangles.add(t.offset(curVertexCount));
       }

       lines.addAll(shape.getLines());

       return this;
   }
    public Mesh asMesh() {
       return new Mesh(vertices, triangles);
    }
    public Wire asWire() {return new Wire(vertices, lines); }
}
