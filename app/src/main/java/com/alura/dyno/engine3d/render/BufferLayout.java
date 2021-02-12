package com.alura.dyno.engine3d.render;

import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class BufferLayout {
    int layoutSize = 0;
    private List<IBufferLayoutElement> elements;

    public BufferLayout()
    {
        elements = new ArrayList<>();
    }

    public void addElement(IBufferLayoutElement element) {
        elements.add(element);
        layoutSize += element.getSize();
    }
    public void removeElement(IBufferLayoutElement element) {
        elements.remove(element);
        layoutSize -= element.getSize();
    }

    public void layoutVertex(Vertex v, FloatBuffer buffer) {
        for(IBufferLayoutElement element : elements) {
            element.layoutVertex(v, buffer);
        }
    }
    public void bind(FloatBuffer buffer, int programHandle) {
        int offset = 0;
        for(IBufferLayoutElement element : elements)
        {
            int attrHandle = GLES20.glGetAttribLocation(programHandle, element.getName());
            GLES20.glEnableVertexAttribArray(attrHandle);
            GLES20.glVertexAttribPointer(attrHandle, element.getCount(), GLES20.GL_FLOAT,
                    element.doNormalize(), layoutSize, buffer.position(offset));

            offset += element.getCount();
        }
    }
    public void unbind(int programHandle) {
        for(IBufferLayoutElement element : elements)
        {
            int attrHandle = GLES20.glGetAttribLocation(programHandle, element.getName());
            GLES20.glDisableVertexAttribArray(attrHandle);
        }
    }

    public int getLayoutSize() {
        return layoutSize;
    }
}
