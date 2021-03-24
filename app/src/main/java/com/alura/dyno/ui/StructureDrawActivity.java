package com.alura.dyno.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.draw.ShapeDrawer;
import com.alura.dyno.engine3d.draw.shapes.Cube;
import com.alura.dyno.engine3d.draw.shapes.Shape;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.buffer.Mesh;
import com.alura.dyno.engine3d.render.shader.Shader;
import com.alura.dyno.engine3d.render.shader.ShaderCompiler;
import com.alura.dyno.engine3d.render.shader.ShaderLoader;
import com.alura.dyno.engine3d.render.shader.ShaderType;
import com.alura.dyno.engine3d.render.shader.SimpleShader;
import com.alura.dyno.engine3d.scene.SceneController;
import com.alura.dyno.engine3d.scene.SceneModel;
import com.alura.dyno.engine3d.scene.SceneView;
import com.alura.dyno.engine3d.script.MeshRenderer;

public class StructureDrawActivity extends Activity {

    SceneView view;
    SceneController controller;
    SceneModel model;
    MeshRenderer renderer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadView();
        loadModel();
        loadController();

    }
    private void loadView() {
        setContentView(R.layout.act_structure_draw);
        view = findViewById(R.id.act_structure_draw_glsurface);
    }

    //TODO Think of a way to load models. Use ROOM?
    private void loadModel() {
        model = new SceneModel();

    }
    private void loadController() {
        controller = new SceneController(view, model, getBaseContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        view.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        view.onResume();
    }

}
