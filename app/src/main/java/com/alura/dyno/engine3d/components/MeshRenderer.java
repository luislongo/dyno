package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.shaders.SimpleShader;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class MeshRenderer<V extends SimpleShader> extends Renderer<MeshBuffer, V> {

    public MeshRenderer(RendererBuilder builder) {
        super(builder);
    }

    @Override
    public void setUniforms() {
        shader.setModelMatrix(getParent().getGlobalTransform().getModelmatrix());
        shader.setViewMatrix(SceneMaster.getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneMaster.getMainCamera().getProjectionMatrix());
    }

    public static class MeshRendererBuilder<T extends MeshRendererBuilder<T, V>, V extends SimpleShader>
            extends RendererBuilder<T, MeshBuffer, V> {
        protected RGBAColor meshColor = ColorPalette.MAGENTA;

        public MeshRendererBuilder(String name, V shader) {
            super(name, shader);
        }

        @Override
        protected void initializeEmptyData() {
            data = new MeshBuffer();
        }

        public MeshRenderer build() {
            return new MeshRenderer(this);
        }

        public static <T extends  MeshRendererBuilder<T,V>, V extends SimpleShader>
            MeshRendererBuilder<T, V> builder(String name, V shader)
        {
            return new MeshRendererBuilder<>(name, shader);
        }

        public T setObjectColor(RGBAColor meshColor) {
            this.meshColor = meshColor;
            return (T) this;
        }
    }

}