package com.alura.dyno.engine3d.system.shaders;

import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class TextShader extends Shader {
    public static final String FONTATLAS_UNIFORM = "u_FontAtlas";
    public static final String FONTSIZE_UNIFORM = "u_FontSize";
    public static final String FONTCOLOR_UNIFORM = "u_FontColor";

    public TextShader(ShaderLoader loader) {
        super(loader);
    }

    public void setFontAtlas(Texture texture) {
        setUniformTexture("u_FontAtlas", texture);
    }
    public void setFontSize(float fontSize) {
        setUniformFloat1("u_FontSize", fontSize);
    }
    public void setFontColor(RGBAColor color) {
        setUniformColor("u_FontColor", color);
    }

}
