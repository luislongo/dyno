package com.alura.dyno.engine3d.vertex;

import android.opengl.GLES20;

public class WireBuffer extends VertexBuffer {
    public WireBuffer() {
        super();
    }

    public void addElement(Vertex v1, Vertex v2) {
        addVertex(v1);
        addVertex(v2);
    }

    @Override
    protected void drawElements() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getVertexCount());
    }
}