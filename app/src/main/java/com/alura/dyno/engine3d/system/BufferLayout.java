package com.alura.dyno.engine3d.system;

import android.os.Debug;

import com.alura.dyno.maths.Vector3;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class BufferLayout {
    List<BufferLayoutElement> elements;
    int offset = 0;
    int stride = 0;

    public BufferLayout()
    {
        elements = new ArrayList<>();
    }

    private void pushFloat(int count) {
        BufferLayoutElement<Float> element = new BufferLayoutElement<Float>(count, offset, false);
        elements.add(element);

        offset += count;
        stride += count * Float.BYTES;
    }
    private void pushInt(int count) {
        BufferLayoutElement element = new BufferLayoutElement<Integer>(count, offset, false);
        elements.add(element);

        offset += count;
        stride += count * Integer.BYTES;
    }
    public void pushVector3()
    {
        pushFloat(3);
    }
    public void pushColor()
    {
        pushFloat(4);
    }
    public void pushVector2()
    {
        pushFloat(2);
    }
    public int getStride() {
        return stride;
    }
}
