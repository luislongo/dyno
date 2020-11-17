package com.alura.dyno.engine3d.components;

import android.opengl.Matrix;

import com.alura.dyno.engine3d.system.SceneMaster;
import com.alura.dyno.engine3d.system.fonts.Font;
import com.alura.dyno.engine3d.system.shaders.TextShader;
import com.alura.dyno.engine3d.utils.RGBAColor;

import org.jetbrains.annotations.NotNull;

public class TextRenderer extends MeshRenderer<TextShader> {

    protected float fontSize = 1.0f;
    protected RGBAColor fontColor = RGBAColor.BLACK;
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
        font.fontAtlas.bind(0);
        shader.setFontAtlas(font.fontAtlas);

        shader.setFontSize(fontSize);
        shader.setFontColor(fontColor);

        shader.setModelMatrix(getParent().getGlobalTransform().getModelmatrix());
        shader.setViewMatrix(SceneMaster.getMainCamera().getViewMatrix());
        shader.setProjectionMatrix(SceneMaster.getMainCamera().getProjectionMatrix());
    }

    private float[] getUMVPMatrix(@NotNull Camera camera) {
        float[] uMVPMatrix = new float[16];

        Matrix.setIdentityM(uMVPMatrix, 0);
        Matrix.multiplyMM(uMVPMatrix, 0, camera.getViewMatrix(), 0,
                getParent().getGlobalTransform().getModelmatrix(), 0);
        Matrix.multiplyMM(uMVPMatrix, 0, camera.getProjectionMatrix(), 0, uMVPMatrix, 0);

        return uMVPMatrix;
    }

    public static class TextRendererBuilder<T extends TextRendererBuilder<T>>
            extends MeshRendererBuilder<T, TextShader> {
        Font font;

        String text = "";
        float fontSize = 1.0f;
        RGBAColor fontColor = RGBAColor.BLACK;

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
    }

}
