package com.alura.dyno.engine3d.utils;

import android.content.Context;

import java.io.InputStream;

public class AndroidResourceLoader {
    Context context;

    public AndroidResourceLoader(Context context) {
        this.context = context;
    }

    public String loadRawResource(int resourceId) {
        InputStream stream = context.getResources().openRawResource(resourceId);
        StringBuilder build = new StringBuilder();

        byte[] buf = new byte[1024];
        int length;

        try {
            while ((length = stream.read(buf)) != -1) {
                build.append(new String(buf, 0, length));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading resource: " + resourceId);
        }

        return build.toString();
    }
}
