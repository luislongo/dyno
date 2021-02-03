package com.alura.dyno.engine3d.vertex;

import java.util.ArrayList;
import java.util.List;

public class Mesh {
    private List<Vertex> vertices;
    private List<Triangle> triangles;

    public Mesh() {
        this.vertices = new ArrayList<>();
        this.triangles = new ArrayList<>();
    }

    public void addVertex(Vertex v) {
        this.vertices.add(v);
    }
    public void addTriangle(Triangle t) {
        this.triangles.add(t);
    }

    public int getTriangleCount() {
        return triangles.size();
    }
    public int getVertexCount() {
        return triangles.size();
    }
    public List<Vertex> getVertices() {return vertices;   }
    public List<Triangle> getTriangles() {return triangles;   }
}
