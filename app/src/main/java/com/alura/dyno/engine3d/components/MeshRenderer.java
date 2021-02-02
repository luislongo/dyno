package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.system.vertex.Mesh;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.system.vertex.Triangle;
import com.alura.dyno.engine3d.system.vertex.Vertex;
import com.alura.dyno.engine3d.system.vertex.VertexTransform;
import com.alura.dyno.maths.Matrix4F;

public class MeshRenderer extends MonoBehaviour {
    public Material material = new DefaultMaterial();
    public Shader shader;

    public Mesh sharedMesh;
    private MeshBuffer buffer;

    public MeshRenderer() {
        super("Mesh Renderer");
    }

    public void setMesh(Mesh mesh) {
        this.sharedMesh = mesh;

    }

    public void invalidate(VertexTransform t) {
        clearBufferData();
        updateBufferData(t);
    }
    private void clearBufferData() {
        buffer.clearData();
    }
    private void updateBufferData(VertexTransform vt) {
        Matrix4F globalMatrix = getParent().getGlobalTransform().getModelmatrix();

        for(Vertex vertex : sharedMesh.getVertices()) {
            buffer.put(vertex.transform(vt));
        }

        for(Triangle triangle : sharedMesh.getTriangles()) {
            buffer.put(triangle);
        }
    }

    @Override
    public void onCreate(ComponentEvent.OnCreateEvent event) {
        super.onCreate(event);


    }
}
