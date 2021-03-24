package com.alura.dyno.engine3d.render.attr;

import com.alura.dyno.engine3d.render.Vertex;

import java.nio.FloatBuffer;

public class UVAttribute implements IAttribute {
    @Override public String getName() {
        return "a_UVs";
    }
    @Override public int getSize() {
        return 2 * Float.BYTES;
    }
    @Override public int getCount() {
        return 2;
    }
    @Override public boolean doNormalize() {
        return false;
    }
    @Override public void layoutVertex(Vertex v, FloatBuffer buffer) {
        buffer.put(v.uvs.toArray());
    }
}
