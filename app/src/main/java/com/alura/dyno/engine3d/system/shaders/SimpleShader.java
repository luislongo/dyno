package com.alura.dyno.engine3d.system.shaders;

public class SimpleShader extends Shader {

    public final static String MODELMATRIX_UNIFORM_NAME = "u_ModelMatrix";
    public final static String VIEWMATRIX_UNIFORM_NAME = "u_ViewMatrix";
    public final static String PROJECTIONMATRIX_UNIFORM_NAME = "u_ProjectionMatrix";

    public SimpleShader(ShaderLoader loader) {
        super(loader);
    }

    public void setModelMatrix(float[] modelMatrix) {
        setUniformMat4(MODELMATRIX_UNIFORM_NAME, modelMatrix);
    }
    public void setViewMatrix(float[] viewMatrix) {
        setUniformMat4(VIEWMATRIX_UNIFORM_NAME, viewMatrix);
    }
    public void setProjectionMatrix(float[] projectionMatrix) {
        setUniformMat4(PROJECTIONMATRIX_UNIFORM_NAME, projectionMatrix);
    }
}
