package com.alura.dyno.engine3d.render.buffer;

import android.opengl.GLES20;
import android.util.Log;

import com.alura.dyno.engine3d.render.Vertex;
import com.alura.dyno.engine3d.render.attributes.IAttribute;

import java.nio.FloatBuffer;
import java.util.Stack;

public class BufferLayout {
    int layoutSize = 0;
    protected Stack<IAttribute> attributes;

    public BufferLayout()
    {
        attributes = new Stack<>();
    }

    public void pushAttribute(IAttribute element) {
        attributes.push(element);
        layoutSize += element.getSize();
    }
    public IAttribute popAttribute() {
        IAttribute element = attributes.lastElement();
        layoutSize -= element.getSize();

        attributes.pop();
        return element;
    }

    public void layoutVertex(Vertex v, FloatBuffer buffer) {
        for(IAttribute element : attributes) {
            element.layoutVertex(v, buffer);
        }
    }
    public void bind(FloatBuffer buffer, int programHandle) {
        int offset = 0;
        for(IAttribute attribute : attributes)
        {
            int attrHandle = GLES20.glGetAttribLocation(programHandle, attribute.getName());

            if(doesHandleExist(attrHandle)) {
                GLES20.glEnableVertexAttribArray(attrHandle);
                GLES20.glVertexAttribPointer(attrHandle, attribute.getCount(), GLES20.GL_FLOAT,
                        attribute.doNormalize(), layoutSize, buffer.position(offset));
            } else {
                Log.w("BUFFER LAYOUT", "Can't find attribute name in shader: " + attribute.getName());
            }

            offset += attribute.getCount();
        }
    }
    public void unbind(int programHandle) {
        for(IAttribute element : attributes)
        {
            int attrHandle = GLES20.glGetAttribLocation(programHandle, element.getName());
            GLES20.glDisableVertexAttribArray(attrHandle);
        }
    }
    public boolean doesHandleExist(int handle) {
        return !(handle==-1);
    }
    public int getLayoutSize() {
        return layoutSize;
    }
}
