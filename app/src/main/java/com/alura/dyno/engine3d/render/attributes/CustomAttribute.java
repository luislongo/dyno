package com.alura.dyno.engine3d.render.attributes;

public abstract class CustomAttribute implements IAttribute {
    String name;
    int size;
    int count;
    boolean doNormalize;

    public CustomAttribute(String name, int size, int count, boolean doNormalize) {
        this.name = name;
        this.size = size;
        this.count = count;
        this.doNormalize = doNormalize;
    }
    @Override public String getName() {
        return this.name;
    }
    @Override public int getSize() {
        return this.size;
    }
    @Override public int getCount() {
        return this.count;
    }
    @Override public boolean doNormalize() {
        return this.doNormalize;
    }
}
