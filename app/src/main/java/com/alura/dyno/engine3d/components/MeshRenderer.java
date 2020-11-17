package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class MeshRenderer<V extends Shader> extends Renderer<MeshBuffer, V> {

    public MeshRenderer(RendererBuilder builder) {
        super(builder);
    }

    @Override
    public void setUniforms() {
        shader.setModelMatrix(getParent().getGlobalTransform().getModelmatrix());
        shader.setViewMatrix(SceneMaster.getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneMaster.getMainCamera().getViewMatrix());
    }

    public static class MeshRendererBuilder<T extends MeshRendererBuilder<T, V>, V extends Shader>
            extends RendererBuilder<T, MeshBuffer, V> {
        protected RGBAColor meshColor = RGBAColor.MAGENTA;

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

        public static <T extends  MeshRendererBuilder<T,V>, V extends Shader>
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
