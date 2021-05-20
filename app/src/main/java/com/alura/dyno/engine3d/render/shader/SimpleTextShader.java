package com.alura.dyno.engine3d.render.shader;

import com.alura.dyno.engine3d.render.attributes.PositionAttribute;
import com.alura.dyno.engine3d.render.attributes.UVAttribute;
import com.alura.dyno.engine3d.render.uniforms.AlbedoUniform;
import com.alura.dyno.engine3d.render.uniforms.ModelMatrixUniform;
import com.alura.dyno.engine3d.render.uniforms.ProjectionMatrixUniform;
import com.alura.dyno.engine3d.render.uniforms.TextColorUniform;
import com.alura.dyno.engine3d.render.uniforms.TextHardEdgeUniform;
import com.alura.dyno.engine3d.render.uniforms.TextSoftEdgeUniform;
import com.alura.dyno.engine3d.render.uniforms.ViewMatrixUniform;
import com.alura.dyno.engine3d.script.Renderer;

import java.util.List;

public class SimpleTextShader extends Shader {
    float hardEdge = 0.45f;
    float softEdge = 0.4f;

    public SimpleTextShader(List<ShaderSource> sources) {
        super(sources);

        initializeLayout();
        initializeUniforms();
    }
    private void initializeUniforms() {
        putUniform(new ModelMatrixUniform());
        putUniform(new ViewMatrixUniform());
        putUniform(new ProjectionMatrixUniform());
        putUniform(new AlbedoUniform());
        putUniform(new TextColorUniform());
        putUniform(new TextHardEdgeUniform() {
            @Override
            public Float getValue(Renderer renderer) {
                return hardEdge;
            }
        });
        putUniform(new TextSoftEdgeUniform() {
            @Override
            public Float getValue(Renderer renderer) {
                return softEdge;
            }
        });
    }
    private void initializeLayout() {
        pushAttribute(new PositionAttribute());
        pushAttribute(new UVAttribute());
    }
}
