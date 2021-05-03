package com.alura.dyno.engine3d.render.uniforms;

public abstract class TextSoftEdgeUniform extends FloatUniform1 {
    public TextSoftEdgeUniform() {
        super();
    }

    @Override
    public String getName() {
        return "u_TextSoftEdge";
    }
}
