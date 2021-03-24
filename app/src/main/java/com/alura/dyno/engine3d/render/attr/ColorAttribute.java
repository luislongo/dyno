package com.alura.dyno.engine3d.render.attr;

import com.alura.dyno.engine3d.render.Vertex;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class ColorAttribute implements IAttribute {
    @Override public String getName() {
        return "a_Color";
    }
    @Override public int getSize() {
        return 4 * Float.BYTES;
    }
    @Override public int getCount() {
        return 4;
    }
    @Override public boolean doNormalize() {
        return false;
    }
    @Override public void layoutVertex(Vertex v, FloatBuffer buffer) {
        buffer.put(v.color.toArray());
    }
}
