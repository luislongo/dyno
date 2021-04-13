package com.alura.dyno.engine3d.render.shader;

import com.alura.dyno.engine3d.render.attr.ColorAttribute;
import com.alura.dyno.engine3d.render.attr.NormalAttribute;
import com.alura.dyno.engine3d.render.attr.PositionAttribute;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.shader.uniforms.UniformGraphicMatrix;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.linalg.Algebra;

import java.util.List;

public class SimpleShader extends Shader {

    public final static String MODELMATRIX_UNIFORM_NAME = "u_ModelMatrix";
    public final static String VIEWMATRIX_UNIFORM_NAME = "u_ViewMatrix";
    public final static String PROJECTIONMATRIX_UNIFORM_NAME = "u_ProjectionMatrix";

    public SimpleShader(List<ShaderSource> sources) {
        super(sources);

        initializeUniforms();
        initializeLayout();
    }
    private void initializeUniforms() {
        setModelMatrix(Algebra.graphicMatrixFactory().identity());
        setViewMatrix(Algebra.graphicMatrixFactory().identity());
        setProjectionMatrix(Algebra.graphicMatrixFactory().identity());
    }
    private void initializeLayout() {
        pushAttribute(new PositionAttribute());
        pushAttribute(new ColorAttribute());
        pushAttribute(new NormalAttribute());
    }
    public void setModelMatrix(GraphicMatrix modelMatrix) {
        putUniform(MODELMATRIX_UNIFORM_NAME, new UniformGraphicMatrix(modelMatrix));
    }
    public void setViewMatrix(GraphicMatrix viewMatrix) {
        putUniform(VIEWMATRIX_UNIFORM_NAME, new UniformGraphicMatrix(viewMatrix));
    }
    public void setProjectionMatrix(GraphicMatrix projectionMatrix) {
        putUniform(PROJECTIONMATRIX_UNIFORM_NAME, new UniformGraphicMatrix(projectionMatrix));
    }
}
