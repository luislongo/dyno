package com.alura.dyno.ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.Matrix;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alura.dyno.R;
import com.alura.dyno.engine3d.input.InputDetector;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.GraphicMatrixFactory;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.graphics.Vector4;
import com.alura.dyno.math.linalg.Algebra;

public class StructureDrawActivity extends Activity {

    SceneView view;
    SceneController controller;
    InputDetector detector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_structure_draw);
        view = findViewById(R.id.act_structure_draw_glsurface);

        testMatrices();

        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        controller = new SceneController();

        if (supportsEs2) {
            view.setEGLContextClientVersion(3);
            view.setController(controller);
            detector = new InputDetector(this, view);
        }
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

    private void testMatrices() {
        float l = -4.0f;
        float r = 1.0f;
        float b = -5.0f;
        float t = 3.0f;
        float n = 0.0f;
        float f = 20.0f;

        float[] orthoAndroidArray = new float[16];
        Matrix.orthoM(orthoAndroidArray, 0, l, r, b, t, n, f);

        GraphicMatrix orthoMine = Algebra.graphicMatrixFactory().orthogonal(l, r, b, t, n, f);
        GraphicMatrix orthoAndroid = new GraphicMatrix(orthoAndroidArray);
    }
}
