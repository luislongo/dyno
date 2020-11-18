package com.alura.dyno.engine3d.system;

import android.opengl.GLES20;

import java.nio.FloatBuffer;

public class BufferLayoutElement {

    //1. Fields
    private final String name;
    private final int count;
    private final int offset;
    private final boolean doNormalize;

    //Constructors
    public BufferLayoutElement(String name, int count, int offset, boolean doNormalize) {
        this.name = name;
        this.count = count;
        this.doNormalize = doNormalize;
        this.offset = offset;
    }

    //Getters and setters
    public int getCount() {
        return count;
    }

    public int getOffset() {
        return offset;
    }

    public boolean doNormalize() {
        return doNormalize;
    }

    public String getName() {
        return name;
    }
    public void bind(FloatBuffer buffer, int programHandle, int stride)
    {
        int attrHandle = GLES20.glGetAttribLocation(programHandle, name);
        GLES20.glEnableVertexAttribArray(attrHandle);
        GLES20.glVertexAttribPointer(attrHandle, count, GLES20.GL_FLOAT,
                doNormalize, stride, buffer.position(offset));
    }
    public void unbind(int programHandle)
    {
        int attrHandle = GLES20.glGetAttribLocation(programHandle, name);
        GLES20.glDisableVertexAttribArray(attrHandle);
    }

}
