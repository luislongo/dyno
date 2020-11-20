package com.alura.dyno.engine3d.system.shaders;

import android.content.Context;

import com.alura.dyno.R;

public class ShaderMaster {

    public static SimpleShader objectShader;
    public static GridShader gridShader;
    public static TextShader textShader;

    private static Context context;

    public static void loadShaders(Context context) {
        ShaderMaster.context = context;

        loadObjectShader();
        loadGridShader();
        loadTextShader();
    }

    private static void loadObjectShader() {
        ShaderLoader shaderLoader = new ShaderLoader(context);
        shaderLoader.load(ShaderType.Vertex, R.raw.shaderv_object);
        shaderLoader.load(ShaderType.Fragment, R.raw.shaderf_object);

        objectShader = new SimpleShader(shaderLoader);
    }

    private static void loadGridShader() {
        ShaderLoader shaderLoader = new ShaderLoader(context);
        shaderLoader.load(ShaderType.Vertex, R.raw.shaderv_grid);
        shaderLoader.load(ShaderType.Fragment, R.raw.shaderf_grid);

        gridShader = new GridShader(shaderLoader);
    }

    private static void loadTextShader() {
        ShaderLoader shaderLoader = new ShaderLoader(context);
        shaderLoader.load(ShaderType.Vertex, R.raw.shaderv_text);
        shaderLoader.load(ShaderType.Fragment, R.raw.shaderf_text);

        textShader = new TextShader(shaderLoader);
    }
}
