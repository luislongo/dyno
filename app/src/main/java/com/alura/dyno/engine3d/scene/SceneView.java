package com.alura.dyno.engine3d.scene;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class SceneView extends GLSurfaceView {
    SceneRenderer renderer;

    public SceneView(Context context) {
        super(context);
        init(context);
    }
    public SceneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2) {
            setEGLContextClientVersion(2);
        }

        renderer = new SceneRenderer(context);
        super.setRenderer(renderer);
    }

    public SceneRenderer getRenderer() {
        return renderer;
    }
}







