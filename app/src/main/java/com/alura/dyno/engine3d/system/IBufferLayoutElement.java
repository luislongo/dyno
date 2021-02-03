package com.alura.dyno.engine3d.system;

import com.alura.dyno.engine3d.vertex.Vertex;
import java.nio.FloatBuffer;

public interface IBufferLayoutElement {
    String getName();
    int getSize();
    int getCount();
    boolean doNormalize();
    void layoutVertex(Vertex v, FloatBuffer buffer);
}
