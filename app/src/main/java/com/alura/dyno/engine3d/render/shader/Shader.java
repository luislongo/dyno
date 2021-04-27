package com.alura.dyno.engine3d.render.shader;

import android.opengl.GLES20;
import android.util.AttributeSet;
import android.util.LayoutDirection;
import android.util.Log;

import com.alura.dyno.engine3d.render.Texture;
import com.alura.dyno.engine3d.render.attr.IAttribute;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.shader.uniforms.Uniform;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.math.MathExtra;
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.graphics.Vector4;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shader {
    HashMap<Integer, Uniform> uniforms;
    private BufferLayout layout;

    private int programHandle;

    public Shader(List<ShaderSource> sources) {
        initializeVariables();
        compileShader(sources);
    }
    private void initializeVariables() {
        uniforms = new HashMap<>();
        layout = new BufferLayout();
    }
    private void compileShader(List<ShaderSource> sources) {
        ShaderCompiler compiler = new ShaderCompiler();

        for(ShaderSource source : sources) {
            compiler.putShader(source);
        }

        programHandle = compiler.compile();
    }

    protected void putUniform(Uniform uniform) {
        int uniformHandle = uniform.getHandleFromShader(this);

        if(uniforms.containsKey(uniformHandle)) {
            uniforms.get(uniformHandle).setValue(uniform.getValue());
        } else
        {
            uniforms.put(uniformHandle, uniform);
        }
    }
    protected void pushAttribute(IAttribute attribute) {
        layout.pushAttribute(attribute);
    }
    public int getProgramId() {
        return programHandle;
    }
    private void bindUniforms() {
        for(Uniform u : uniforms.values()) {
            u.bind();
        }
    }
    private void setUniforms() {
        for (HashMap.Entry<Integer, Uniform> entry : uniforms.entrySet()) {
            entry.getValue().insertInto(entry.getKey());
        }
    }
    private void unbindUniforms() {
        for(Uniform u : uniforms.values()) {
            u.unbind();
        }
    }

    public BufferLayout getLayout() {
        return layout;
    }

    public final void use(FloatBuffer vbo) {
        GLES20.glUseProgram(programHandle);

        layout.bind(vbo, this.programHandle);
        bindUniforms();
        setUniforms();
    }
    public final void unuse() {
        GLES20.glUseProgram(0);

        unbindUniforms();
    }

}
