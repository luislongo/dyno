package com.alura.dyno.engine3d.render;

import java.nio.FloatBuffer;

public interface IBufferLayoutElement {
    String getName();
    int getSize();
    int getCount();
    boolean doNormalize();
    void layoutVertex(Vertex v, FloatBuffer buffer);
}
