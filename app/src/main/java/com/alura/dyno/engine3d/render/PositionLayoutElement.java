package com.alura.dyno.engine3d.render;

import java.nio.FloatBuffer;

public class PositionLayoutElement implements IBufferLayoutElement {
    @Override public String getName() {
        return "a_Position";
    }
    @Override public int getSize() {
        return 3 * Float.BYTES;
    }
    @Override public int getCount() {
        return 3;
    }
    @Override public boolean doNormalize() {
        return false;
    }
    @Override public void layoutVertex(Vertex v, FloatBuffer buffer) {
        buffer.put(v.position.toArray());
    }
}
