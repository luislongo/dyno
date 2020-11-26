package com.alura.dyno.engine3d.system.shaders;

import com.alura.dyno.maths.Matrix4F;

public class SimpleShader extends Shader {

    public final static String MODELMATRIX_UNIFORM_NAME = "u_ModelMatrix";
    public final static String VIEWMATRIX_UNIFORM_NAME = "u_ViewMatrix";
    public final static String PROJECTIONMATRIX_UNIFORM_NAME = "u_ProjectionMatrix";

    public SimpleShader(ShaderLoader loader) {
        super(loader);
    }

    public void setModelMatrix(Matrix4F modelMatrix) {
        setUniformMat4F(MODELMATRIX_UNIFORM_NAME, modelMatrix);
    }
    public void setViewMatrix(Matrix4F viewMatrix) {
        setUniformMat4F(VIEWMATRIX_UNIFORM_NAME, viewMatrix);
    }
    public void setProjectionMatrix(Matrix4F projectionMatrix) {
        setUniformMat4F(PROJECTIONMATRIX_UNIFORM_NAME, projectionMatrix);
    }
}
