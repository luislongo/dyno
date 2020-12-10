package com.alura.dyno.maths;

public class MathExtra {

    public static float clamp(float value, float minValue, float maxValue) {
        return (float) Math.min(Math.max(value, minValue), maxValue);
    }
    public static float map(float value, float min, float max, float mapMin, float mapMax) {
        return mapMin + (mapMax - mapMin) * (value - min) / (max - min);
    }

}
