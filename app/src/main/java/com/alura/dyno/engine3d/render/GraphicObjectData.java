package com.alura.dyno.engine3d.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class GraphicObjectData<FACE extends IFaceAbstraction> {
    private final List<Vertex> vertices;
    private final List<FACE> faces;

    public GraphicObjectData() {
        vertices = new ArrayList<>();
        faces = new ArrayList<>();
    }
    public GraphicObjectData(Vertex[] vertices, FACE[] faces) {
        this.vertices = Arrays.asList(vertices);
        this.faces  = Arrays.asList(faces);
    }
    public GraphicObjectData(List<Vertex> vertices, List<FACE> faces) {
        this.vertices = new ArrayList<>(vertices);
        this.faces = new ArrayList<>(faces);
    }
    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }
    public void addVertex(Vertex[] vertex) {
        this.vertices.addAll(Arrays.asList(vertex));
    }
    public void addVertex(List<Vertex> vertex) {
        this.vertices.addAll(vertex);
    }
    public void addFace(FACE face) {this.faces.add(face);}
    public void addFace(FACE[] face) {this.faces.addAll(Arrays.asList(face));}
    public void addFace(List<FACE> face) {this.faces.addAll(face);}

    public int getFaceCount() {
        return faces.size();
    }
    public int getVertexCount() {
        return vertices.size();
    }
    public List<Vertex> getVertices() { return vertices;   }
    public List<FACE> getFaces() {return faces;   }
}
