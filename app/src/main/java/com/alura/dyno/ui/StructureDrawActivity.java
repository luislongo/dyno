package com.alura.dyno.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.utils.InputDetector;

public class StructureDrawActivity extends Activity {

    StructureDrawGLSurface drawSurface;
    InputDetector detector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_structure_draw);
        super.onCreate(savedInstanceState);

        drawSurface = findViewById(R.id.act_structure_draw_glsurface);

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2) {
            drawSurface.setEGLContextClientVersion(3);
            drawSurface.setRenderer(new StructureDrawRenderer(getBaseContext()));

            detector = new InputDetector(this);
            drawSurface.setListener(detector);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        drawSurface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        drawSurface.onResume();
    }
}
