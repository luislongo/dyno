package com.alura.dyno.engine3d.shader;

import com.alura.dyno.engine3d.shader.uniforms.Uniform;
import com.alura.dyno.math.graphics.GraphicMatrix;

import java.util.List;

public class SimpleShader extends Shader {

    public final static String MODELMATRIX_UNIFORM_NAME = "u_ModelMatrix";
    public final static String VIEWMATRIX_UNIFORM_NAME = "u_ViewMatrix";
    public final static String PROJECTIONMATRIX_UNIFORM_NAME = "u_ProjectionMatrix";

    private GraphicMatrix modelMatrix;
    private GraphicMatrix viewMatrix;
    private GraphicMatrix projectionMatrix;

    public SimpleShader(List<ShaderSource> sources) {
        super(sources);
    }

    @Override public void setUniforms() {
        setUniformMat4F(new Uniform<GraphicMatrix>(MODELMATRIX_UNIFORM_NAME, modelMatrix));
        setUniformMat4F(new Uniform<GraphicMatrix>(VIEWMATRIX_UNIFORM_NAME, viewMatrix));
        setUniformMat4F(new Uniform<GraphicMatrix>(PROJECTIONMATRIX_UNIFORM_NAME, projectionMatrix));
    }
    public void setModelMatrix(GraphicMatrix modelMatrix) {
        this.modelMatrix = new GraphicMatrix(modelMatrix);
    }
    public void setViewMatrix(GraphicMatrix viewMatrix) {
        this.viewMatrix = new GraphicMatrix(viewMatrix);
    }
    public void setProjectionMatrix(GraphicMatrix projectionMatrix) {
        this.projectionMatrix = new GraphicMatrix(projectionMatrix);
    }
}
