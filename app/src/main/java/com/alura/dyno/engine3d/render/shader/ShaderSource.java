package com.alura.dyno.engine3d.render.shader;

public class ShaderSource {
    private ShaderType type;
    private String source;

    public ShaderSource(ShaderType type, String source) {
        this.type = type;
        this.source = source;
    }

    public ShaderType getType() {
        return type;
    }

    public String getSource() {
        return source;
    }


}
