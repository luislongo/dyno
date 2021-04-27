package com.alura.dyno.engine3d.render.shader;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.attr.ColorAttribute;
import com.alura.dyno.engine3d.render.attr.NormalAttribute;
import com.alura.dyno.engine3d.render.attr.PositionAttribute;
import com.alura.dyno.engine3d.render.attr.UVAttribute;
import com.alura.dyno.engine3d.render.shader.uniforms.AlbedoUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.GraphicMatrixUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.ModelMatrixUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.ProjectionMatrixUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.TextureUniform;
import com.alura.dyno.engine3d.render.shader.uniforms.ViewMatrixUniform;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.linalg.Algebra;

import java.util.List;

public class SimpleShader extends Shader {

    public SimpleShader(List<ShaderSource> sources) {
        super(sources);

        initializeUniforms();
        initializeLayout();
    }
    private void initializeUniforms() {
        putUniform(new ModelMatrixUniform(Algebra.graphicMatrixFactory().identity()));
        putUniform(new ViewMatrixUniform(Algebra.graphicMatrixFactory().identity()));
        putUniform(new ProjectionMatrixUniform(Algebra.graphicMatrixFactory().identity()));

        //TODO Find a null texture;
        putUniform(new AlbedoUniform());
    }
    private void initializeLayout() {
        pushAttribute(new PositionAttribute());
        pushAttribute(new ColorAttribute());
        pushAttribute(new NormalAttribute());
        pushAttribute(new UVAttribute());
    }

}
