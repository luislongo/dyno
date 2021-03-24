package com.alura.dyno.engine3d.render.buffer;

import com.alura.dyno.engine3d.render.GraphicObjectData;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;

import java.util.List;

public class Mesh extends GraphicObjectData<Triangle> {

    public Mesh() {
    }

    public Mesh(Vertex[] vertices, Triangle[] triangles) {
        super(vertices, triangles);
    }

    public Mesh(List<Vertex> vertices, List<Triangle> triangles) {
        super(vertices, triangles);
    }
}


