package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.text.Font;

public class TextRenderer extends Renderer<Mesh> {
    private String text;
    private Font font;

    public TextRenderer(String name, Font font) {
        super(name);
        this.font = font;
    }

    @Override
    public int getDrawMode() {
        return GLES20.GL_TRIANGLES;
    }
    public void setText(String text) {
        this.text = text;
        this.setData(font.layOutQuads(text));
    }
}
