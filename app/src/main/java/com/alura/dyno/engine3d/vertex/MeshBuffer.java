package com.alura.dyno.engine3d.vertex;

import com.alura.dyno.engine3d.system.BufferLayout;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public abstract class MeshBuffer {

    private FloatBuffer vertexBuffer;
    private IntBuffer triangleBuffer;
    private BufferLayout layout;

    public MeshBuffer() {
    }

    public void setLayout(BufferLayout layout) {
        this.layout = layout;
    }
    public void allocateMemory(int vertexCount, int triangleCount) {
        vertexBuffer = ByteBuffer.allocateDirect(vertexCount * layout.getLayoutSize())
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        triangleBuffer = ByteBuffer.allocateDirect(3 * triangleCount * Integer.BYTES)
                .order(ByteOrder.nativeOrder()).asIntBuffer();
    }

    public void put(Vertex v) {
        layout.layoutVertex(v, vertexBuffer);
    }
    public void put(Triangle t) {
        triangleBuffer.put(t.i).put(t.j).put(t.k);
    }
}


