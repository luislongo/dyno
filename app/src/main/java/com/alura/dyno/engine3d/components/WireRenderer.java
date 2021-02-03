package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.shaders.SimpleShader;
import com.alura.dyno.engine3d.shaders.Shader;
import com.alura.dyno.engine3d.vertex.WireBuffer;

public class WireRenderer<U extends SimpleShader> extends Renderer<WireBuffer, U> {

    public WireRenderer(WireRendererBuilder builder) {
        super(builder);
    }

    @Override
    protected void setUniforms() {
        shader.setModelMatrix(getParent().getGlobalTransform().getModelmatrix());
        shader.setViewMatrix(SceneMaster.getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneMaster.getMainCamera().getProjectionMatrix());
    }

    public static class WireRendererBuilder<T extends WireRenderer.WireRendererBuilder<T, V>, V extends Shader>
            extends RendererBuilder<T, WireBuffer, V> {

        protected WireRendererBuilder(String name, V shader) {
            super(name, shader);
        }

        public WireRenderer build() {
            return new WireRenderer(this);
        }

        @Override
        protected void initializeEmptyData() {
            data = new WireBuffer();
        }
    }
}
