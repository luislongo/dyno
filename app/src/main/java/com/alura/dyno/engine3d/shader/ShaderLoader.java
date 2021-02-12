package com.alura.dyno.engine3d.shader;

import android.content.Context;

import com.alura.dyno.engine3d.utils.AndroidResourceLoader;

import java.util.ArrayList;
import java.util.List;

public class ShaderLoader {
    List<ShaderSource> sources;
    Context context;

    public ShaderLoader(Context context)
    {
        this.context = context;
        sources = new ArrayList<>();
    }
    public void loadFromRawResource(ShaderType type, int shaderResourceId) {
        String strSource = parseFile(shaderResourceId);
        sources.add(new ShaderSource(type, strSource));
    }
    private String parseFile(int shaderResourceId) {
        AndroidResourceLoader loader = new AndroidResourceLoader(context);
        return loader.loadRawResource(shaderResourceId);
    }
    public List<ShaderSource> getSources() {
        return sources;
    }
}
