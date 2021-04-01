package com.alura.dyno.engine3d.draw;

import com.alura.dyno.engine3d.draw.samplers.IColorSampler;
import com.alura.dyno.engine3d.draw.shapes.Shape;
import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.render.buffer.Wire;
import com.alura.dyno.engine3d.tree.TreeNode;
import com.alura.dyno.math.graphics.Axii;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.linalg.Algebra;

import java.util.ArrayList;
import java.util.List;

public class ShapeDrawer {
   public Axii axii;
   List<Vertex> vertices;
   List<Triangle> triangles;
   List<Line> lines;

   DrawOptions options;
   GraphicMatrix preMatrix;

   public ShapeDrawer() {
       axii = new Axii();
       vertices = new ArrayList<>();
       triangles = new ArrayList<>();
       lines = new ArrayList<>();

       options = new DrawOptions();
       preMatrix = Algebra.graphicMatrixFactory().identity();
   }

   public ShapeDrawer addShape(Shape shape) {
       int curVertexCount = vertices.size();
       vertices.addAll(shape.getVertices(options));

       for(Triangle t : shape.getTriangles()) {
           triangles.add(t.offset(curVertexCount));
       }

       lines.addAll(shape.getLines());

       return this;
   }

   public void setColorSampler(IColorSampler sampler) {
       options.colorSampler = sampler;
   }
   public Mesh asMesh() {
       return new Mesh(vertices, triangles);
    }
    public Wire asWire() {return new Wire(vertices, lines); }
}
