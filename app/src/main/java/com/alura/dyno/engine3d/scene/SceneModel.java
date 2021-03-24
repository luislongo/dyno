package com.alura.dyno.engine3d.scene;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.glyph.Camera;
import com.alura.dyno.engine3d.glyph.Glyph;
import com.alura.dyno.engine3d.render.shader.Shader;

public class SceneModel {
    Glyph root;
    Shader shader;
    Camera mainCamera;

    public SceneModel() {
        createRoot();
    }

    private void createRoot() {
        root = new Glyph("ROOT");
    }
    public Glyph getRoot() {
        return root;
    }
    public void setShader(Shader shader) {
        this.shader = shader;
    }
    public Shader getShader()
    {
        return shader;
    }
    public Camera getMainCamera() {return mainCamera;}
    public void setMainCamera(Camera mainCamera) {this.mainCamera = mainCamera;}
}
