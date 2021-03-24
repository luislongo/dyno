package com.alura.dyno.engine3d.draw.shapes;

import com.alura.dyno.engine3d.draw.DrawOptions;
import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.math.graphics.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class Shape {
    protected List<Vector3> positions;
    protected List<Vector3> normals;
    protected List<Triangle> triangles;
    protected List<Line> lines;

    public Shape() {
        initializeVariables();
    }
    private void initializeVariables() {
        positions = new ArrayList<>();
        normals = new ArrayList<>();
        triangles = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public List<Vertex> getVertices(DrawOptions options) {
        List<Vertex> vertices = new ArrayList<>();
        int nrOfVertices = positions.size();

        for(int i = 0; i < nrOfVertices; i++) {
            Vector3 sampleAt = positions.get(i).clone();

            Vertex v = new VertexBuilder()
                    .setPosition(positions.get(i).multiply(options.preMatrix, 1.0f))
                    .setNormal(normals.get(i).multiply(options.preMatrix, 0.0f))
                    .setColor(options.colorSampler.sampleAt(sampleAt))
                    .setUVs(options.uvSampler.sampleAt(sampleAt))
                    .build();

            vertices.add(v);
        }

        return vertices;
    }
    public List<Triangle> getTriangles() {
        return triangles;
    }
    public List<Line> getLines() {
        return lines;
    }

    public void invalidate() {
        clearData();
        calculateData();
    }
    private void clearData() {
        positions.clear();
        normals.clear();
        triangles.clear();
        lines.clear();
    }
    private void calculateData() {
        calculatePositions();
        calculateNormals();
        calculateTriangles();
        calculateLines();
    }

    protected abstract void calculatePositions();
    protected abstract void calculateNormals();
    protected abstract void calculateTriangles();
    protected abstract void calculateLines();
}
