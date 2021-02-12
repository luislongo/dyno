package com.alura.dyno.engine3d.text;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Font {

    public final char INVALID_CHAR_REPLACEMENT = '?';

    public HashMap<Integer, FontCharacter> symbols;
    public Texture fontAtlas;

    private int lineHeight;
    private int base;
    private int scaleW;
    private int scaleH;

    public Font(FontBuilder builder) {

        this.symbols = builder.symbols;
        this.fontAtlas = builder.fontAtlas;

        this.lineHeight = builder.lineHeight;
        this.base = builder.base;
        this.scaleW = builder.scaleH;
        this.scaleH = builder.scaleW;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public int getBase() {
        return base;
    }

    public int getScaleW() {
        return scaleW;
    }

    public int getScaleH() {
        return scaleH;
    }

    public FontCharacter find(char ch) {
        int key = ch;

        if (symbols.containsKey(key)) {
            return symbols.get(key);
        } else {
            return symbols.get((int) INVALID_CHAR_REPLACEMENT);
        }

    }

    public Vertex[] layOutQuads(String text) {
        ArrayList<Vertex> quads = new ArrayList<>();
        float cursorX = 0.0f;

        for (char ch : text.toCharArray()) {
            FontCharacter fontCh = find(ch);
            quads.addAll(Arrays.asList(calculateCharQuad(fontCh, cursorX)));

            cursorX += fontCh.xadvance;
        }

        return quads.toArray(new Vertex[4 * text.length()]);
    }

    private Vertex[] calculateCharQuad(FontCharacter fontCh, float cursorX) {
        float left = cursorX + fontCh.left;
        float right = cursorX + fontCh.right;
        float top = fontCh.top;
        float bottom = fontCh.bottom;

        Vertex v1 = new Vertex.VertexBuilder()
                .setPosition(new Vector3(left, bottom, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvLeft, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v2 = new Vertex.VertexBuilder()
                .setPosition(new Vector3(right, bottom, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvRight, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v3 = new Vertex.VertexBuilder()
                .setPosition(new Vector3(right, top, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvLeft, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v4 = new Vertex.VertexBuilder()
                .setPosition(new Vector3(left, top, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvLeft, fontCh.uvTop))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();

        return new Vertex[]{v1,v2,v3,v4};
    }

    public static class FontBuilder {
        private HashMap<Integer, FontCharacter> symbols;
        private Texture fontAtlas;

        private int lineHeight;
        private int base;
        private int scaleW;
        private int scaleH;

        public FontBuilder() {
            symbols = new HashMap<>();
        }

        public Font build() {
            return new Font(this);
        }

        public FontBuilder setFontAtlas(Texture fontAtlas) {
            this.fontAtlas = fontAtlas;

            return this;
        }

        public FontBuilder addCharacter(FontCharacter character) {
            this.symbols.put(character.id, character);

            return this;
        }

        public FontBuilder setLineHeight(int lineHeight) {
            this.lineHeight = lineHeight;

            return this;
        }

        public FontBuilder setBase(int base) {
            this.base = base;

            return this;
        }

        public FontBuilder setScale(int scaleH, int scaleW) {
            this.scaleH = scaleH;
            this.scaleW = scaleW;

            return this;
        }
    }
}
