package com.alura.dyno.engine3d.utils;

import android.graphics.Color;

import com.alura.dyno.math.graphics.FloatVector;

public class RGBAColor extends FloatVector<RGBAColor> {
    public static RGBAColor RED            = new RGBAColor(1.0f, 0.0f, 0.0f, 1.0f);
    public static RGBAColor BLUE           = new RGBAColor(0.0f, 0.0f, 1.0f, 1.0f);
    public static RGBAColor GREEN          = new RGBAColor(0.0f, 1.0f, 0.0f, 1.0f);
    public static RGBAColor BLACK          = new RGBAColor(0.0f, 0.0f, 0.0f, 1.0f);
    public static RGBAColor WHITE          = new RGBAColor(1.0f, 1.0f, 1.0f, 1.0f);
    public static RGBAColor MIDNIGHT_BLUE  = new RGBAColor(0.035f, 0.094f, 0.2f, 1.0f);
    public static RGBAColor WASHED_BLUE    = RGBAColor.from255(92, 109, 141, 255);
    public static RGBAColor ACQUA_GREEN    = new RGBAColor(0.510f, 0.878f, 0.749f, 1.0f);
    public static RGBAColor MAGENTA        = RGBAColor.fromHex("#FF00FF");

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
    public static RGBAColor from255(int r255, int g255, int b255, int a255) {
        float r = (float) r255 / 255f;
        float g = (float) g255 / 255f;
        float b = (float) b255 / 255f;
        float a = (float) a255 / 255f;

        return new RGBAColor(r, g, b, a);
    }
    public static RGBAColor fromHex(String colorStr) {
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
