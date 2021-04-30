package com.alura.dyno.engine3d.script;

import com.alura.dyno.engine3d.render.buffer.Mesh;

public class TextRenderer extends Renderer<Mesh> {
    private String text;

    @Override
    public int getDrawMode() {
        return 0;
    }
    public void setText(String text) {

    }
}
