package com.alura.dyno.engine3d.system.shaders;

import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class TextShader extends SimpleShader {
    public static final String FONTATLAS_UNIFORM = "u_FontAtlas";
    public static final String FONTCOLOR_UNIFORM = "u_FontColor";
    public static final String FONTSIZE_UNIFORM = "u_FontSize";

    public TextShader(ShaderLoader loader) {
        super(loader);
    }

    public void setFontAtlas(Texture fontAtlas) {
        setUniformTexture(FONTATLAS_UNIFORM, fontAtlas);
    }
    public void setFontColor(RGBAColor fontColor) {
        setUniformColor(FONTCOLOR_UNIFORM, fontColor);
    }
    public void setFontSize(float fontSize) {
        setUniform1F(FONTSIZE_UNIFORM, fontSize);}
}
