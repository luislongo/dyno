package com.alura.dyno.engine3d.components;

import android.content.Context;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.events.ComponentEvent;
import com.alura.dyno.engine3d.system.shaders.GridShader;
import com.alura.dyno.engine3d.system.vertex.MeshBuffer;
import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class AdaptableGridRenderer extends GridRenderer {

    int maxTexturePixelSize;
    int minTexturePixelSize;

    public AdaptableGridRenderer(AdaptableGridRendererBuilder builder) {
        super(builder);

        minTexturePixelSize = builder.minTexturePixelSize;
        maxTexturePixelSize = builder.maxTexturePixelSize;
    }

    @Override
    public void onUpdate(ComponentEvent.OnUpdateEvent event) {
        super.onUpdate(event);
        if(SceneMaster.getMainCamera().fromViewToScreen(spacing) <= minTexturePixelSize)
        {
            spacing *= 5;
        }

        if(SceneMaster.getMainCamera().fromViewToScreen(spacing) >= maxTexturePixelSize)
        {
            spacing /= 5;
        }
    }

    public static class AdaptableGridRendererBuilder<T extends AdaptableGridRendererBuilder<T>>
            extends GridRendererBuilder<T> {
        int maxTexturePixelSize;
        int minTexturePixelSize;


        public AdaptableGridRendererBuilder(String name, GridShader shader, Context context) {
            super(name, shader, context);
        }

        public AdaptableGridRenderer build() {
            return new AdaptableGridRenderer(this);
        }

        public T setMaxTexturePixelSize(int maxTexturePixelSize)
        {
            this.maxTexturePixelSize = maxTexturePixelSize;

            return (T) this;
        }

        public T setMinTexturePixelSize(int minTexturePixelSize)
        {
            this.minTexturePixelSize = minTexturePixelSize;

            return (T) this;
        }

        public static AdaptableGridRendererBuilder<?> builder(String name, GridShader shader, Context context)
        {
            return new AdaptableGridRendererBuilder<>(name, shader, context);
        }
    }
}
