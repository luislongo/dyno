package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.shader.Shader;
import com.alura.dyno.engine3d.render.Mesh;
import com.alura.dyno.engine3d.render.MeshBuffer;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;

public class MeshRenderer extends Script {
    public Shader shader;

    public Mesh sharedMesh;
    private MeshBuffer buffer;

    public MeshRenderer() {
        super("Mesh Renderer");
    }

    public void setMesh(Mesh mesh) {
        this.sharedMesh = mesh;
    }

    public void invalidate() {
        clearBufferData();
        updateBufferData();
    }
    private void clearBufferData() {
        buffer.clear();
    }
    private void updateBufferData() {
        for(Vertex vertex : sharedMesh.getVertices()) {
            buffer.put(vertex);
        }
        for(Triangle triangle : sharedMesh.getTriangles()) {
            buffer.put(triangle);
        }
    }
}
