package com.vector.vrpc.example;

/**
 * @author smq
 */

public class CalcSeviceImpl implements CalcSevice {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }
}
