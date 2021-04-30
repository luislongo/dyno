package com.alura.dyno.engine3d.text;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.VertexBuilder;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;

import org.jetbrains.annotations.NotNull;

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

    public FontCharacter find(char ch) {
        int key = ch;

        if (symbols.containsKey(key)) {
            return symbols.get(key);
        } else {
            return symbols.get((int) INVALID_CHAR_REPLACEMENT);
        }

    }

    public Mesh layOutQuads(String text) {
        Mesh mesh = new Mesh();

        float cursorX = 0.0f;

        int last = 0;
        for (char ch : text.toCharArray()) {
            FontCharacter fontCh = find(ch);

            mesh.addVertex(Arrays.asList(calculateCharQuad(fontCh, cursorX)));
            mesh.addFace(new Triangle(last, last + 1, last + 2));
            mesh.addFace(new Triangle(last, last + 2, last + 3));

            cursorX += fontCh.xadvance;
            last += 4;
        }

        return mesh;
    }

    @NotNull
    private Vertex[] calculateCharQuad(FontCharacter fontCh, float cursorX) {
        float left = cursorX + fontCh.left;
        float right = cursorX + fontCh.right;
        float top = fontCh.top;
        float bottom = fontCh.bottom;

        Vertex v1 = new VertexBuilder()
                .setPosition(new Vector3(left, bottom, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvLeft, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v2 = new VertexBuilder()
                .setPosition(new Vector3(right, bottom, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvRight, fontCh.uvBottom))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v3 = new VertexBuilder()
                .setPosition(new Vector3(right, top, 0))
                .setNormal(new Vector3(0.0f, 0.0f, 1.0f))
                .setUVs(new Vector2(fontCh.uvRight, fontCh.uvTop))
                .setColor(new RGBAColor(1.0f,1.0f,1.0f,1.0f))
                .build();
        Vertex v4 = new VertexBuilder()
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
