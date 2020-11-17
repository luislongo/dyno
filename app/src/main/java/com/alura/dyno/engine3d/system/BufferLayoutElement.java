package com.alura.dyno.engine3d.system;

public class BufferLayoutElement<T> {

    //1. Fields
    private final int count;
    private final int offset;
    private final boolean isNormal;

    //Constructors
    public BufferLayoutElement(int count, int offset, boolean normal) {
        this.count = count;
        this.isNormal = normal;
        this.offset = offset;
    }

    //Getters and setters
    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isNormal() {
        return isNormal;
    }

}
