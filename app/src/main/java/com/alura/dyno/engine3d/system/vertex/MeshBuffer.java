package com.alura.dyno.engine3d.system.vertex;

import android.opengl.GLES20;

public class MeshBuffer extends VertexBuffer {

    public MeshBuffer() {
        super();
    }

    public MeshBuffer addElement(Vertex v1, Vertex v2, Vertex v3) {
        addVertex(v1);
        addVertex(v2);
        addVertex(v3);

        return this;
    }
    
    @Override
    public void drawElements() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, getVertexCount());
    }
}
