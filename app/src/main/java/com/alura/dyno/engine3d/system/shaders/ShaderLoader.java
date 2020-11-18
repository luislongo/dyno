package com.alura.dyno.engine3d.system.shaders;

import android.content.Context;

import java.io.InputStream;
import java.util.HashMap;

public class ShaderLoader {
    HashMap<ShaderType, String> sources;
    Context context;

    public ShaderLoader(Context context)
    {
        this.context = context;
        sources = new HashMap<>();
    }
    public void load(ShaderType type, int shaderResourceId) {
        String shaderSource = parseFile(shaderResourceId);
        sources.put(type, shaderSource);
    }
    private String parseFile(int shaderResourceId) {
        InputStream stream = context.getResources().openRawResource(shaderResourceId);
        StringBuilder build = new StringBuilder();

        byte[] buf = new byte[1024];
        int length;

        try {
            while ((length = stream.read(buf)) != -1) {
                build.append(new String(buf, 0, length));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading resource: " + shaderResourceId);
        }

        return build.toString();
    }



    public String getVertex()
    {
        return sources.get(ShaderType.Vertex);
    }


    public String getFragment()
    {
        return sources.get(ShaderType.Fragment);
    }
}
