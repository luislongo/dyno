package com.alura.dyno.engine3d.render.uniforms;

public abstract class TextHardEdgeUniform extends FloatUniform1 {
    public TextHardEdgeUniform() {
        super();
    }

    @Override
    public String getName() {
        return "u_TextHardEdge";
    }
}
