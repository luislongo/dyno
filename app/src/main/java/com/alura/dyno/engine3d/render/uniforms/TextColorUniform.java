package com.alura.dyno.engine3d.render.uniforms;

import com.alura.dyno.engine3d.script.Renderer;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class TextColorUniform extends ColorUniform {
    public TextColorUniform() {
        super();
    }

    @Override
    public RGBAColor getValue(Renderer renderer) {
        return renderer.getMaterial().getAlbedoColor();
    }

    @Override
    public String getName() {
        return "u_TextColor";
    }
}
