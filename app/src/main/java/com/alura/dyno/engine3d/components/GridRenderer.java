package com.alura.dyno.engine3d.components;

import android.content.Context;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.system.shaders.GridShader;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.system.vertex.Vertex;
import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.engine3d.utils.TriangleFactory;
import com.alura.dyno.maths.MatrixG;

public class GridRenderer extends Renderer<MeshBuffer, GridShader> {
    protected float spacing;
    Context context;

    private Texture texture;
    private RGBAColor lineColor;
    private RGBAColor backgroundColor;

    public GridRenderer(GridRendererBuilder builder) {
        super(builder);

        this.context = builder.context;
        this.lineColor = builder.lineColor;
        this.backgroundColor = builder.backgroundColor;
        this.spacing = builder.spacing;

        createQuad();
        loadTexture(builder.textureResourceId, context);
    }

    private void createQuad() {
        Vertex[] vertexData = new TriangleFactory.QuadBuilder(-1.0f, 1.0f, -1.0f, 1.0f)
                .setUVBounds(0.0f, 1.0f, 0.0f, 1.0f)
                .setDepth(1.0f).asVertex();
        data.addVertex(vertexData);
    }
    private void loadTexture(int textureResourceId, Context context) {
        texture = new Texture(textureResourceId, context);
    }

    @Override
    public void setUniforms() {
        MatrixG inverseVP = getInverseVPMatrix();

        texture.bind(0);
        shader.setBackgroundColor(backgroundColor);
        shader.setLineColor(lineColor);
        shader.setGridSpacing(spacing);
        shader.setInverseVPMatrix(inverseVP);
        shader.setGridTexture(texture);
    }
    private MatrixG getInverseVPMatrix() {
        MatrixG inverseVP = SceneMaster.getMainCamera().getVPMatrix();
        inverseVP.invert();

        return inverseVP;
    }

    public static class GridRendererBuilder<T extends GridRendererBuilder<T>>
            extends RendererBuilder<T, MeshBuffer, GridShader> {
        protected int textureResourceId = -1;
        protected RGBAColor lineColor = ColorPalette.WHITE;
        protected RGBAColor backgroundColor =  ColorPalette.BLACK;
        protected float spacing = 1.0f;
        Context context;

        public GridRendererBuilder(String name, GridShader shader, Context context) {
            super(name, shader);
            this.context = context;
        }

        public GridRenderer build() {
            return new GridRenderer(this);
        }

        public T setLineColor(RGBAColor lineColor) {
            this.lineColor = lineColor;

            return (T) this;
        }
        public T setBackgroundColor(RGBAColor backgorundColor) {
            this.backgroundColor = backgorundColor;

            return (T) this;
        }
        public T setShader(GridShader shader) {
            this.shader = shader;

            return (T) this;
        }
        public T setTexture(int textureResourceId) {
            this.textureResourceId = textureResourceId;

            return (T) this;
        }
        public T setSpacing(float spacing) {
            this.spacing = spacing;

            return (T) this;
        }

        @Override
        protected void initializeEmptyData() {
            data = new MeshBuffer();
        }
    }
}
