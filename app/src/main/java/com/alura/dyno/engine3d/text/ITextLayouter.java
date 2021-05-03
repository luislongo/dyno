package com.alura.dyno.engine3d.text;

import com.alura.dyno.engine3d.render.buffer.Mesh;

public interface ITextLayouter<T extends Object> {
    Mesh layoutText(Font font, T object);
}
