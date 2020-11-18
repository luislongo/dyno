package com.alura.dyno.engine3d.system;

import android.os.Debug;

import com.alura.dyno.engine3d.system.shaders.Shader;
import com.alura.dyno.engine3d.system.vertex.VertexBuffer;
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

    private void pushFloat(String name, int count) {
        BufferLayoutElement element = new BufferLayoutElement(name, count, offset, false);
        elements.add(element);

        offset += count;
        stride += count * Float.BYTES;
    }
    public void pushVector3(String name)
    {
        pushFloat(name, 3);
    }
    public void pushColor(String name)
    {
        pushFloat(name, 4);
    }
    public void pushVector2(String name)
    {
        pushFloat(name, 2);
    }
    public void bind(FloatBuffer buffer, int programHandle) {
        for(BufferLayoutElement element : elements)
        {
            element.bind(buffer, programHandle, stride);
        }
    }
    public void unbind(int programHandle) {
        for(BufferLayoutElement element : elements)
        {
            element.unbind(programHandle);
        }
    }

}
