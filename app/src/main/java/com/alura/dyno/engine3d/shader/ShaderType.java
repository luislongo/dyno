package com.alura.dyno.engine3d.shader;

import android.opengl.GLES20;

public enum ShaderType {
    Fragment(GLES20.GL_FRAGMENT_SHADER),
    Vertex(GLES20.GL_VERTEX_SHADER);

    public final int type;

    ShaderType(int type)
    {
        this.type = type;
    }

    public String toString() {
        switch (this)
        {
            case Fragment :
                return "Fragment";
            case Vertex :
                return "Vertex";
            default :
                return "Invalid";
        }
    }
}
