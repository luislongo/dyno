package com.alura.dyno.engine3d.utils;

public class CircularCounter {
    int minValue;
    int maxValue;
    int curValue;

    public CircularCounter(int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.curValue = minValue;
    }

    public int oneUp() {
        this.curValue = clamp(curValue + 1, minValue, maxValue);

        return curValue;
    }

    public int oneDown() {
        this.curValue = clamp(curValue - 1, minValue, maxValue);

        return curValue;
    }

    public int clamp(int value, int minValue, int maxValue) {
        if (value < minValue) {
            return maxValue;
        } else if (value > maxValue) {
            return minValue;
        }
        return value;
    }
}
