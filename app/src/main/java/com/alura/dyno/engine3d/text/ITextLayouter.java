package com.alura.dyno.engine3d.text;

import com.alura.dyno.engine3d.render.buffer.Mesh;

public interface ITextLayouter {
    Mesh layoutText(Font font, String text);
}
