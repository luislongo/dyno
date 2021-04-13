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
import com.alura.dyno.math.graphics.GraphicMatrix;
import com.alura.dyno.math.graphics.Vector2;
import com.alura.dyno.math.graphics.Vector3;
import com.alura.dyno.math.graphics.Vector4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shader {
    HashMap<String, Uniform> uniforms;
    private BufferLayout layout;

    private int programHandle;

    public Shader(List<ShaderSource> sources) {
        initializeVariables();
        compileShader(sources);
    }
    private void initializeVariables()
    {
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

    protected void putUniform(String name, Uniform uniform) {
        if(uniforms.containsKey(name)) {
            uniforms.get(name).setValue(uniform.getValue());
        } else
        {
            int handle = GLES20.glGetUniformLocation(programHandle, name);

            if(doesHandleExist(handle)) {
                uniform.setHandle(handle);
                uniforms.put(name, uniform);
            } else {
                Log.e("SHADER", "SHADER::UNIFORM::COULD NOT FIND UNIFORM::NAME: " + name);
            }

        }
    }
    protected void pushAttribute(IAttribute attribute) {
        layout.pushAttribute(attribute);
    }
    private boolean doesHandleExist(int handle) {
        return !(handle == -1);
    }

    public int getProgramId() {
        return programHandle;
    }
    public void setUniforms() {
        for(Uniform u : uniforms.values()) {
            u.insertInto();
        }
    }
    public BufferLayout getLayout() {
        return layout;
    }
    public final void use() {
        GLES20.glUseProgram(programHandle);
        setUniforms();
    }
    public final void unuse() {
        GLES20.glUseProgram(0);
    }

}
