package com.alura.dyno.engine3d.utils;

import android.graphics.Color;

import com.alura.dyno.maths.VectorF;

public class RGBAColor extends VectorF<RGBAColor> {

    public RGBAColor(float r, float g, float b, float a) {
        super(new float[] {r,g,b,a});
    }
    public RGBAColor(RGBAColor color) {
        super(color.toArray());
    }

    public float r() {
        return getX_(0);
    }
    public float g() {
        return getX_(1);
    }
    public float b() {
        return getX_(2);
    }
    public float a() {
        return getX_(3);
    }

    public static RGBAColor fromIntColor(int color) {
        float r = Color.red(color) / 255f;
        float g = Color.green(color) / 255f;
        float b = Color.blue(color) / 255f;
        float a = Color.alpha(color) / 255f;

        return new RGBAColor(r, g, b, a);
    }
    public static RGBAColor from255Color(int r255, int g255, int b255, int a255) {
        float r = (float) r255 / 255f;
        float g = (float) g255 / 255f;
        float b = (float) b255 / 255f;
        float a = (float) a255 / 255f;

        return new RGBAColor(r, g, b, a);
    }
    public static RGBAColor fromHexColor(String colorStr) {
        return new RGBAColor(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16),
                1.0f);
    }

    @Override
    public RGBAColor clone() {
        return new RGBAColor(this);
    }
}
