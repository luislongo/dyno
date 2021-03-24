package com.alura.dyno.engine3d.render.attr;

import com.alura.dyno.engine3d.render.Vertex;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public interface IAttribute {
    String getName();
    int getSize();
    int getCount();
    boolean doNormalize();
    void layoutVertex(Vertex v, FloatBuffer buffer);
}
