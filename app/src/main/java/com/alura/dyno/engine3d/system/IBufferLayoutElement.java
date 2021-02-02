package com.alura.dyno.engine3d.system;

import com.alura.dyno.engine3d.system.vertex.Vertex;
import com.alura.dyno.maths.Matrix4F;

import java.nio.FloatBuffer;

public interface IBufferLayoutElement {
    String getName();
    int getSize();
    int getCount();
    boolean doNormalize();
    void layoutVertex(Vertex v, FloatBuffer buffer);
}
