package com.alura.dyno.engine3d.script;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.buffer.Wire;
import com.alura.dyno.engine3d.scene.SceneController;

public class WireRenderer extends Renderer<Wire> {

    public WireRenderer(String name) {
        super(name);
    }

    @Override public int getDrawMode() {
        return GLES20.GL_LINES;
    }
    public void setWire(Wire wire) {
        this.sharedData = wire;
    }
}
