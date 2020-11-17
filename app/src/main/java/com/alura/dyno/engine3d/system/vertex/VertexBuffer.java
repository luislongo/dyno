package com.alura.dyno.engine3d.system.vertex;

import com.alura.dyno.engine3d.system.BufferLayout;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.Vector2;
import com.alura.dyno.maths.Vector3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class VertexBuffer {
    protected List<Vector3> positions;
    protected List<RGBAColor> colors;
    protected List<Vector2> uvs;

    FloatBuffer vertexDataBuffer;
    BufferLayout bufferLayout;
    boolean isInGPU = false;

    public VertexBuffer() {
        positions = new ArrayList<>();
        colors = new ArrayList<>();
        uvs = new ArrayList<>();

        createBufferLayout();
    }
    protected abstract void drawElements();
    public final void draw() {
        if (!isInGPU) {
            loadToGPU();
        }

        drawElements();
    }
    public void loadToGPU() {
        clearFloatBuffer();
        allocateMemory();
        loadVerticesIntoDataBuffer();
        resetDataBufferPosition();
    }

    public void clearData() {
        positions.clear();
        colors.clear();
        uvs.clear();

        if (vertexDataBuffer != null) {
            vertexDataBuffer.clear();
        }
    }

    public final void addVertex(Vertex v) {
        positions.add(v.position);
        colors.add(v.color);
        uvs.add(v.uv);

        isInGPU = false;
    }
    public final void addVertex(Vertex[] vertices) {
        for (Vertex v : vertices) {
            addVertex(v);
        }
    }

    private void clearFloatBuffer() {
        if (vertexDataBuffer != null) {
            vertexDataBuffer.clear();
        }
        isInGPU = false;
    }
    private void allocateMemory() {
        vertexDataBuffer = ByteBuffer.allocateDirect(getVertexCount() * Vertex.BYTES)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
    }
    private void loadVerticesIntoDataBuffer() {
        for (int i = 0; i < getVertexCount(); i++) {
            vertexDataBuffer.put(positions.get(i).toArray())
                    .put(colors.get(i).toArray())
                    .put(uvs.get(i).toArray());
        }
        isInGPU = true;
    }
    private void resetDataBufferPosition() {
        vertexDataBuffer.position(0);
    }

    private void createBufferLayout() {
        bufferLayout = new BufferLayout();
        bufferLayout.pushVector3();
        bufferLayout.pushColor();
        bufferLayout.pushVector2();
    }
    public int getVertexCount() {
        return positions.size();
    }
}
