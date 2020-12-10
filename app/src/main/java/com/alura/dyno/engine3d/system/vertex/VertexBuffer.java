package com.alura.dyno.engine3d.system.vertex;

import com.alura.dyno.engine3d.components.Transform;
import com.alura.dyno.engine3d.system.BufferLayout;
import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.Vector2G;
import com.alura.dyno.maths.Vector3G;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class VertexBuffer {
    public final static String POSITION_ATTR_NAME = "a_Position";
    public final static String COLOR_ATTR_NAME = "a_Color";
    public final static String TEXTURE_ATTR_NAME = "a_TextureCoords";

    protected List<Vector3G> positions;
    protected List<RGBAColor> colors;
    protected List<Vector2G> uvs;

    FloatBuffer vertexDataBuffer;
    BufferLayout bufferLayout;
    boolean isInGPU = false;

    public VertexBuffer() {
        positions = new ArrayList<>();
        colors = new ArrayList<>();
        uvs = new ArrayList<>();

        createBufferLayout();
    }
    private void createBufferLayout() {
        bufferLayout = new BufferLayout();
        bufferLayout.pushVector3(VertexBuffer.POSITION_ATTR_NAME);
        bufferLayout.pushColor(VertexBuffer.COLOR_ATTR_NAME);
        bufferLayout.pushVector2(VertexBuffer.TEXTURE_ATTR_NAME);
    }
    protected abstract void drawElements();

    public final void draw(Shader shader, Transform transform) {
        if (!isInGPU) {
            loadToGPU(transform);
        }

        bufferLayout.bind(vertexDataBuffer, shader.getProgramHandle());
        drawElements();
        bufferLayout.unbind(shader.getProgramHandle());
    }

    public void loadToGPU(Transform transform) {
        clearFloatBuffer();
        allocateMemory();
        loadVerticesIntoDataBuffer(transform);
        resetDataBufferPosition();
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

    public void clearData() {
        positions.clear();
        colors.clear();
        uvs.clear();

        if (vertexDataBuffer != null) {
            vertexDataBuffer.clear();
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
    private void loadVerticesIntoDataBuffer(Transform transform) {
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
    public int getVertexCount() {
        return positions.size();
    }
    public List<Vector3G> getPositions()
    {
        return positions;
    }
}
