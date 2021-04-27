package com.alura.dyno.engine3d.render.shader;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.attr.ColorAttribute;
import com.alura.dyno.engine3d.render.attr.NormalAttribute;
import com.alura.dyno.engine3d.render.attr.PositionAttribute;
import com.alura.dyno.engine3d.render.attr.UVAttribute;
import com.alura.dyno.engine3d.render.shader.uniforms.GraphicMatrixUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.ModelMatrixUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.ProjectionMatrixUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.TextureUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.ViewMatrixUniform;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.linalg.Algebra;

import java.util.List;

public class SimpleShader extends Shader {

    public final static String TEXTURE_UNIFORM_NAME = "u_Texture";

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
        pushAttribute(new UVAttribute());
    }

    public void setModelMatrix(GraphicMatrix modelMatrix) {
        putUniform(new ModelMatrixUniform(modelMatrix));
    }
    public void setViewMatrix(GraphicMatrix viewMatrix) {
        putUniform(new ViewMatrixUniform(viewMatrix));
    }
    public void setProjectionMatrix(GraphicMatrix projectionMatrix) {
        putUniform(new ProjectionMatrixUniform(projectionMatrix));
    }
    public void setTexture(Texture texture) {
        putUniform(new TextureUniform(texture) {

            @Override public String getName() {
                return TEXTURE_UNIFORM_NAME;
            }
        });
    }
}
