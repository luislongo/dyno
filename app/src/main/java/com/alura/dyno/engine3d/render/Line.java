package com.alura.dyno.engine3d.render;

public class Line implements IFaceAbstraction {
    private int[] ij = new int[2];

    public Line(int i, int j) {
        this.ij[0] = i;
        this.ij[1] = j;
    }

    @Override
    public int[] getIndices() {
        return ij;
    }
}
