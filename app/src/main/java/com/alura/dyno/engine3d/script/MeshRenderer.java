package com.alura.dyno.engine3d.script;

import android.content.Context;
import android.opengl.GLES20;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.render.Material;
import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.Triangle;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.scene.SceneController;

public class MeshRenderer extends Renderer<Mesh> {
    private Texture texture;

    public MeshRenderer(String name) {
        super(name);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override public int getDrawMode() {
        return GLES20.GL_TRIANGLES;
    }

}
