package com.alura.dyno.engine3d.render;

public class Triangle implements IFaceAbstraction {
    private int[] ijk = new int[3];

    public Triangle(int i, int j, int k) {
        this.ijk[0] = i;
        this.ijk[1] = j;
        this.ijk[2] = k;
    }

    public Triangle offset(int offset) {
        ijk[0] += offset;
        ijk[1] += offset;
        ijk[2] += offset;
        return this;
    }
    @Override public int[] getIndices() {
        return ijk;
    }
    public int[] getIndices(int offset) {
        return new int[] {ijk[0] + offset, ijk[1] + offset, ijk[2] + offset};
    }
}
