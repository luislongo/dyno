package com.alura.dyno.engine3d.system.shaders;

import com.alura.dyno.engine3d.system.shaders.uniforms.Uniform;
import com.alura.dyno.maths.Matrix4F;

import java.util.List;

public class SimpleShader extends Shader {

    public final static String MODELMATRIX_UNIFORM_NAME = "u_ModelMatrix";
    public final static String VIEWMATRIX_UNIFORM_NAME = "u_ViewMatrix";
    public final static String PROJECTIONMATRIX_UNIFORM_NAME = "u_ProjectionMatrix";

    private Matrix4F modelMatrix;
    private Matrix4F viewMatrix;
    private Matrix4F projectionMatrix;

    public SimpleShader(List<ShaderSource> sources) {
        super(sources);
    }

    @Override public void setUniforms() {
        setUniformMat4F(new Uniform<Matrix4F>(MODELMATRIX_UNIFORM_NAME, modelMatrix));
        setUniformMat4F(new Uniform<Matrix4F>(VIEWMATRIX_UNIFORM_NAME, viewMatrix));
        setUniformMat4F(new Uniform<Matrix4F>(PROJECTIONMATRIX_UNIFORM_NAME, projectionMatrix));
    }
    public void setModelMatrix(Matrix4F modelMatrix) {
        this.modelMatrix = new Matrix4F(modelMatrix);
    }
    public void setViewMatrix(Matrix4F viewMatrix) {
        this.viewMatrix = new Matrix4F(viewMatrix);
    }
    public void setProjectionMatrix(Matrix4F projectionMatrix) {
        this.projectionMatrix = new Matrix4F(projectionMatrix);
    }
}
