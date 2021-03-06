package com.alura.dyno.engine3d.render.shader;

import com.alura.dyno.engine3d.render.attributes.PositionAttribute;
import com.alura.dyno.engine3d.render.attributes.UVAttribute;
import com.alura.dyno.engine3d.render.uniforms.AlbedoUniform;
import com.alura.dyno.engine3d.render.uniforms.ModelMatrixUniform;
import com.alura.dyno.engine3d.render.uniforms.ProjectionMatrixUniform;
import com.alura.dyno.engine3d.render.uniforms.ViewMatrixUniform;

import java.util.List;

public class SimpleShader extends Shader {

    public SimpleShader(List<ShaderSource> sources) {
        super(sources);

        initializeUniforms();
        initializeLayout();
    }
    private void initializeUniforms() {
        putUniform(new ModelMatrixUniform());
        putUniform(new ViewMatrixUniform());
        putUniform(new ProjectionMatrixUniform());
        putUniform(new AlbedoUniform());
    }
    private void initializeLayout() {
        pushAttribute(new PositionAttribute());
        pushAttribute(new UVAttribute());
    }

}
