package com.alura.dyno.ui;

import com.alura.dyno.engine3d.eventsystem.TreeEventDispatcher;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.glyph.ICamera;
import com.alura.dyno.engine3d.text.Font;

public class SceneModel {
    Glyph root;
    public ICamera mainCamera;
    public TreeEventDispatcher dispatcher;
    Font font;

    public Glyph getRoot() {
        return root;
    }

    public ICamera getMainCamera() {
        return mainCamera;
    }

    public TreeEventDispatcher getDispatcher() {
        return dispatcher;
    }

    public Font getFont() {
        return font;
    }



}
