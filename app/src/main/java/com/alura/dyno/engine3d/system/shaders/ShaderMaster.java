package com.alura.dyno.engine3d.system.shaders;

import android.content.Context;

import com.alura.dyno.R;

public class ShaderMaster {

    public static Shader objectShader;
    private static Context context;

    public static void loadShaders(Context context) {
        ShaderMaster.context = context;

        loadObjectShader();
    }

    private static void loadObjectShader() {
        ShaderLoader shaderLoader = new ShaderLoader(context);
        shaderLoader.load(ShaderType.Vertex, R.raw.shaderv_object);
        shaderLoader.load(ShaderType.Fragment, R.raw.shaderf_object);

        objectShader = new Shader(shaderLoader);
    }
}
