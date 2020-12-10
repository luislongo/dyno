package com.alura.dyno.engine3d.system.shaders;

import com.alura.dyno.maths.MatrixG;

public class SimpleShader extends Shader {

    public final static String MODELMATRIX_UNIFORM_NAME = "u_ModelMatrix";
    public final static String VIEWMATRIX_UNIFORM_NAME = "u_ViewMatrix";
    public final static String PROJECTIONMATRIX_UNIFORM_NAME = "u_ProjectionMatrix";

    public SimpleShader(ShaderLoader loader) {
        super(loader);
    }

    public void setModelMatrix(MatrixG modelMatrix) {
        setUniformMat4G(MODELMATRIX_UNIFORM_NAME, modelMatrix);
    }
    public void setViewMatrix(MatrixG viewMatrix) {
        setUniformMat4G(VIEWMATRIX_UNIFORM_NAME, viewMatrix);
    }
    public void setProjectionMatrix(MatrixG projectionMatrix) {
        setUniformMat4G(PROJECTIONMATRIX_UNIFORM_NAME, projectionMatrix);
    }
}
