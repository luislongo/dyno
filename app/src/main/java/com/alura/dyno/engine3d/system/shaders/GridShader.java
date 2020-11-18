package com.alura.dyno.engine3d.system.shaders;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.utils.RGBAColor;

public class GridShader extends ShaderBase {
    public final static String BACKGROUNDCOLOR_UNIFORM = "u_BackgroundColor";
    public final static String LINECOLOR_UNIFORM = "u_LineColor";
    public final static String GRIDSPACING_UNIFORM = "u_GridSpacing";
    public final static String GRIDTEXTURE_UNIFORM = "u_GridTexture";
    public final static String INVERSEVPMATRIX_UNIFORM = "u_InverseVPMatrix";

    public GridShader(ShaderLoader loader) {
        super(loader);
    }

    public void setBackgroundColor(RGBAColor color) {
        setUniformColor(BACKGROUNDCOLOR_UNIFORM, color);
    }
    public void setLineColor(RGBAColor color) {
        setUniformColor(LINECOLOR_UNIFORM, color);
    }
    public void setGridSpacing(float spacing)
    {
        setUniformFloat1(GRIDSPACING_UNIFORM, spacing);
    }
    public void setGridTexture(Texture texture)
    {
        setUniformTexture(GRIDTEXTURE_UNIFORM, texture);
    }
    public void setInverseVPMatrix(float[] inverseVPMatrix) {setUniformMat4(INVERSEVPMATRIX_UNIFORM, inverseVPMatrix);}
}
