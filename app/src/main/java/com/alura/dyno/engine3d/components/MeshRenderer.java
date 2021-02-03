package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.shaders.Shader;
import com.alura.dyno.engine3d.vertex.Mesh;
import com.alura.dyno.engine3d.vertex.MeshBuffer;
import com.alura.dyno.engine3d.vertex.Triangle;
import com.alura.dyno.engine3d.vertex.Vertex;
import com.alura.dyno.engine3d.vertex.VertexTransform;
import com.alura.dyno.math.graphics.GraphicMatrix;

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
        GraphicMatrix globalMatrix = getParent().getGlobalTransform().getModelmatrix();

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
