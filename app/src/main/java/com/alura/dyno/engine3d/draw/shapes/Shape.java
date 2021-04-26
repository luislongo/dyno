package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.draw.deformer.IDeformer;
import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
    protected List<Vertex> vertices;

    protected List<Triangle> triangles;
    protected List<Line> lines;

    public Shape() {
        initializeVariables();
        calculateVertices();
        calculateTriangles();
        calculateLines();
    }
    private void initializeVariables() {
        vertices = new ArrayList<>();

        triangles = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public List<Vertex> getVertices() {
        return vertices;
    }
    public List<Triangle> getTriangles() {
        return triangles;
    }
    public List<Line> getLines() {
        return lines;
    }

    public void addDeformation(IDeformer deformation) {
        for(Vertex v : vertices) {
            deformation.deform(v);
        }
    }

    public void invalidate() {
        clearData();
        calculateData();
    }
    private void clearData() {
        vertices.clear();
        triangles.clear();
        lines.clear();
    }
    private void calculateData() {
        calculateVertices();
        calculateTriangles();
        calculateLines();
    }
    protected abstract void calculateVertices();
    protected abstract void calculateTriangles();
    protected abstract void calculateLines();
}
