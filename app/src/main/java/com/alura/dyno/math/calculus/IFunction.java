package com.alura.dyno.math.calculus;

public interface IFunction<IN, OUT> {
    OUT evaluateAt(IN in);
}
