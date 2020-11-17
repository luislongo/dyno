package com.alura.dyno.engine3d.utils;

import android.graphics.Color;

public class RGBAColor {

    //1.2 Constants
    public final static RGBAColor RED = new RGBAColor(1.0f, 0.0f, 0.0f, 1.0f);
    public final static RGBAColor BLUE = new RGBAColor(0.0f, 0.0f, 1.0f, 1.0f);
    public final static RGBAColor GREEN = new RGBAColor(0.0f, 1.0f, 0.0f, 1.0f);
    public final static RGBAColor BLACK = new RGBAColor(0.0f, 0.0f, 0.0f, 1.0f);
    public final static RGBAColor WHITE = new RGBAColor(1.0f, 1.0f, 1.0f, 1.0f);
    public final static RGBAColor MIDNIGHT_BLUE = new RGBAColor(0.035f, 0.094f, 0.2f, 1.0f);
    public final static RGBAColor WASHED_BLUE = new RGBAColor(92f / 255, 109f / 255, 141f / 255, 1.0f);
    public final static RGBAColor ACQUA_GREEN = new RGBAColor(0.510f, 0.878f, 0.749f, 1.0f);
    public final static RGBAColor MAGENTA = RGBAColor.fromHexColor("#FF00FF");
    //1. Fields
    //1.1 Variables
    public float r, g, b, a;

    //2. Methods
    //2.1 Constructor
    public RGBAColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public static RGBAColor fromIntColor(int color) {
        float r = Color.red(color) / 255f;
        float g = Color.green(color) / 255f;
        float b = Color.blue(color) / 255f;
        float a = Color.alpha(color) / 255f;

        return new RGBAColor(r, g, b, a);
    }

    public static RGBAColor fromHexColor(String colorStr) {
        return new RGBAColor(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16),
                1.0f);
    }

    public float[] toArray() {
        return new float[]{r, g, b, a};
    }
}
