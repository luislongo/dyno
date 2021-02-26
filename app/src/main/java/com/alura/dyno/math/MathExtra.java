package com.alura.dyno.math;

public class MathExtra {

    public static float clamp(float value, float minValue, float maxValue) {
        return (float) Math.min(Math.max(value, minValue), maxValue);
    }
    public static float map(float value, float min, float max, float mapMin, float mapMax) {
        return mapMin + (mapMax - mapMin) * (value - min) / (max - min);
    }
    public static int assertArrayEquals(float[] a1, float[] a2, float delta) {
        if(a1.length != a2.length) {
            return -2;
        } else {
            for(int i = 0; i < a1.length; i++) {
                if(Math.abs(a1[i] - a2[i]) > delta) {
                    return i;
                }
            }
            return -1;
        }
    }

    public static class DeltaCompare {
        float delta;

        public DeltaCompare(float delta) {
            this.delta = delta;
        }

        public boolean isZero(double a) {return Math.abs(a) <= delta;}
        public boolean isZero(float a) {return isZero(a);}
        public boolean equals(float a, float b)
        {
            return (Math.abs(a - b) <= delta);
        }
        public float roundToDelta(float a) {
            return Math.round( a / delta) * delta;
        }
    }
}
