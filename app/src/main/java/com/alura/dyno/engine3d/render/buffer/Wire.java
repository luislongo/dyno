package com.alura.dyno.engine3d.render.buffer;

import com.alura.dyno.engine3d.render.Line;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.buffer.GraphicObjectData;

import java.util.List;

public class Wire extends GraphicObjectData<Line> {

    public Wire(Vertex[] vertices, Line[] lines) {
        super(vertices, lines);
    }
    public Wire(List<Vertex> vertices, List<Line> lines) {
        super(vertices, lines);
    }

    @Override
    public int getVertexPerFace() {
        return 2;
    }

}
