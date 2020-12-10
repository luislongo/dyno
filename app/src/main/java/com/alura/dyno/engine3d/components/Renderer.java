package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.system.vertex.VertexBuffer;

public abstract class Renderer<U extends VertexBuffer, V extends Shader>
        extends MonoBehaviour
        implements ComponentEvent.IOnRenderEventListener {

    U data;
    V shader;

    public Renderer(RendererBuilder builder) {
        super(builder);

        if (builder.data == null) {
            builder.initializeEmptyData();
        }

        this.data = (U) builder.data;
        this.shader = (V) builder.shader;
    }

    protected abstract void setUniforms();

    @Override
    public void onCreate(ComponentEvent.OnCreateEvent event) {
        super.onCreate(event);
        data.loadToGPU(getParent().getGlobalTransform());
    }

    @Override
    public void onUpdate(ComponentEvent.OnUpdateEvent event) {
        super.onUpdate(event);
    }

    @Override
    public void onRender() {
        shader.use();
        setUniforms();
        data.draw(shader, getParent().getGlobalTransform());
        shader.unuse();
    }

    public final void clearData() {
        data.clearData();
    }

    public abstract static class RendererBuilder<T extends RendererBuilder<T, U, V>, U extends VertexBuffer, V extends Shader>
            extends MonoBehaviourBuilder<T> {
        protected U data;
        protected V shader;

        protected RendererBuilder(String name, V shader) {
            super(name);
            this.shader = shader;
        }

        protected abstract void initializeEmptyData();

        public T setData(U data) {
            this.data = data;

            return (T) this;
        }
    }
}