package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;
import android.view.textclassifier.TextLanguage;

import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.text.Font;
import com.alura.dyno.engine3d.text.ITextLayouter;

public class TextRenderer extends Renderer<Mesh> {
    private String text;
    private Font font;
    private ITextLayouter layouter;

    public TextRenderer(String name, Font font, ITextLayouter layouter) {
        super(name);
        this.font = font;
        this.layouter = layouter;
    }

    @Override
    public int getDrawMode() {
        return GLES20.GL_TRIANGLES;
    }
    public void setText(String text) {
        this.text = text;
        this.setData(layouter.layoutText(font, text));
    }
}
