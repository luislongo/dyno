package com.alura.dyno.engine3d.render.shader;

import android.opengl.GLES20;

import com.alura.dyno.engine3d.render.attr.IAttribute;
import com.alura.dyno.engine3d.render.buffer.BufferLayout;
import com.alura.dyno.engine3d.render.shader.uniforms.Uniform;
import com.alura.dyno.engine3d.script.Renderer;

import java.util.HashMap;
import java.util.List;

public class Shader {
    HashMap<Integer, Uniform> uniforms;
    private BufferLayout layout;
    private int handle;

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

        handle = compiler.compile();
    }

    protected void putUniform(Uniform uniform) {
        int uniformHandle = uniform.getHandleFromShader(this);

        if(uniforms.containsKey(uniformHandle)) {
            uniforms.remove(uniformHandle);
        }

        uniforms.put(uniformHandle, uniform);
    }
    protected void pushAttribute(IAttribute attribute) {
        layout.pushAttribute(attribute);
    }

    public void setUniformsFromRenderer(Renderer renderer) {
        for (HashMap.Entry<Integer, Uniform> entry : uniforms.entrySet()) {
            entry.getValue().insertInto(entry.getKey(), renderer);
        }
    }

    public int getHandle() {
        return handle;
    }
    public BufferLayout getLayout() {
        return layout;
    }

    public final void use() {
        GLES20.glUseProgram(handle);

    }
    public final void unuse() {
        GLES20.glUseProgram(0);
    }
    public final void bind(Renderer renderer) {
        layout.bind(renderer.getVBO(), this.handle);
        renderer.getMaterial().bind();
    }
    public final void unbind(Renderer renderer) {
        layout.unbind(this.handle);
        renderer.getMaterial().unbind();
    }

}
