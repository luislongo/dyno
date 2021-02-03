package com.alura.dyno.engine3d.components;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.fonts.Font;
import com.alura.dyno.engine3d.shaders.TextShader;
import com.alura.dyno.engine3d.utils.ColorPalette;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class TextRenderer extends MeshRenderer<TextShader> {

    protected float fontSize;
    protected RGBAColor fontColor;
    protected Font font;
    private String text;

    public TextRenderer(TextRendererBuilder builder) {
        super(builder);

        this.font = builder.font;
        this.text = builder.text;
        this.fontColor = builder.fontColor;
        this.fontSize = builder.fontSize;

        data.addVertex(font.layOutQuads(text));
    }

    public void updateText(String text) {
        clearData();
        data.addVertex(font.layOutQuads(text));

        this.text = text;
    }

    @Override
    public void setUniforms() {
        shader.setFontSize(fontSize);
        shader.setModelMatrix(getParent().getGlobalTransform().getModelmatrix());
        shader.setViewMatrix(SceneMaster.getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneMaster.getMainCamera().getProjectionMatrix());

        font.fontAtlas.bind(0);
        shader.setFontAtlas(font.fontAtlas);
        shader.setFontColor(fontColor);
    }

    public static class TextRendererBuilder<T extends TextRendererBuilder<T>>
            extends MeshRendererBuilder<T, TextShader> {
        Font font;
        String text = "";
        float fontSize = 1.0f;
        RGBAColor fontColor = ColorPalette.BLACK;

        public TextRendererBuilder(String name, TextShader shader, Font font) {
            super(name, shader);

            this.name = name;
            this.font = font;
        }

        public TextRenderer build() {
            return new TextRenderer(this);
        }

        public T setText(String text) {
            this.text = text;

            return (T) this;
        }

        public T setFontSize(float fontSize) {
            this.fontSize = fontSize;

            return (T) this;
        }

        public T setFontColor(RGBAColor fontColor) {
            this.fontColor = fontColor;

            return (T) this;
        }

        public static TextRendererBuilder<?> builder(String name, TextShader shader, Font font)
        {
            return new TextRendererBuilder<>(name, shader, font);
        }
    }

}
