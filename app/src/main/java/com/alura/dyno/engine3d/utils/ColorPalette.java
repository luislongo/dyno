package com.alura.dyno.engine3d.utils;

public interface ColorPalette {
    RGBAColor RED   = new RGBAColor(1.0f, 0.0f, 0.0f, 1.0f);
    RGBAColor BLUE  = new RGBAColor(0.0f, 0.0f, 1.0f, 1.0f);
    RGBAColor GREEN = new RGBAColor(0.0f, 1.0f, 0.0f, 1.0f);
    RGBAColor BLACK = new RGBAColor(0.0f, 0.0f, 0.0f, 1.0f);
    RGBAColor WHITE = new RGBAColor(1.0f, 1.0f, 1.0f, 1.0f);
    RGBAColor MIDNIGHT_BLUE = new RGBAColor(0.035f, 0.094f, 0.2f, 1.0f);
    RGBAColor WASHED_BLUE = RGBAColor.from255Color(92, 109, 141, 255);
    RGBAColor ACQUA_GREEN = new RGBAColor(0.510f, 0.878f, 0.749f, 1.0f);
    RGBAColor MAGENTA = RGBAColor.fromHexColor("#FF00FF");
}
