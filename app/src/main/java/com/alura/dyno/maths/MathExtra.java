package com.alura.dyno.maths;

public class MathExtra {

    public static float clamp(float value, float minValue, float maxValue)
    {
        return (float) Math.min(Math.max(value, minValue), maxValue);
    }
}
