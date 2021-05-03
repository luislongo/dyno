package com.alura.dyno.engine3d.render.uniforms;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.script.Renderer;

public class AlbedoUniform extends TextureUniform {
    public AlbedoUniform() {
        super();
    }

    @Override
    public Texture getValue(Renderer renderer) {
        return renderer.getMaterial().getAlbedo();
    }

    @Override
    public String getName() {
        return "u_Albedo";
    }
}
