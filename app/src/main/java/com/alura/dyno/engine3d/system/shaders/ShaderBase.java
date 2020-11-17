package com.alura.dyno.engine3d.system.shaders;

import android.opengl.GLES20;
import android.util.Log;

import com.alura.dyno.engine3d.system.Texture;
import com.alura.dyno.engine3d.utils.RGBAColor;
import com.alura.dyno.maths.Vector2;
import com.alura.dyno.maths.Vector3;

import java.util.HashMap;
import java.util.Map;

public abstract class ShaderBase {
    public final static String POSITION_ATTR_NAME = "a_Position";
    public final static String COLOR_ATTR_NAME = "a_Color";
    public final static String TEXTURE_ATTR_NAME = "a_Position";

    HashMap<ShaderType, Integer> shaderHandles;
    HashMap<String, Integer> uniformHandles;
    int programHandle;

    public ShaderBase(ShaderLoader loader) {
        shaderHandles = new HashMap<>();
        uniformHandles = new HashMap<>();

        createProgram();
        compileAllShaders(loader.sources);
        attachAllShaders();
        linkProgram();
    }

    private void createProgram()
    {
        programHandle = GLES20.glCreateProgram();
    }
    private void compileAllShaders(HashMap<ShaderType, String> sources) {
        for(Map.Entry<ShaderType, String> kvp : sources.entrySet())
        {
            compileShader(kvp.getKey(), kvp.getValue());
        }
    }
    private void attachAllShaders() {
        for(Integer handle : shaderHandles.values())
        {
            GLES20.glAttachShader(programHandle, handle);
        }
    }
    private void deleteAllShaders() {
        for(Integer handle : shaderHandles.values())
        {
            GLES20.glDeleteShader(handle);
        }
    }

    private void compileShader(ShaderType type, String shaderSource) {
        int shaderHandle = createShader(type);

        loadShaderSourceIntoGPU(shaderSource, shaderHandle);
        compileShader(shaderHandle);

        if(!hasCompiled(shaderHandle))
        {
            printShaderCompilingError(shaderHandle, type);
            GLES20.glDeleteShader(shaderHandle);
        } else
        {
            shaderHandles.put(type, shaderHandle);
        }
    }
    private int createShader(ShaderType type) {
        int shaderHandle = GLES20.glCreateShader(type.type);
        return shaderHandle;
    }
    private void loadShaderSourceIntoGPU(String shaderSource, int shaderHandle) {
        GLES20.glShaderSource(shaderHandle, shaderSource);
    }
    private void compileShader(int shaderHandle) {
        GLES20.glCompileShader(shaderHandle);
    }
    private boolean hasCompiled(int shaderHandle) {
        int[] isCompiled = new int[1];
        GLES20.glGetShaderiv(shaderHandle, GLES20.GL_COMPILE_STATUS, isCompiled, 0);
        if(isCompiled[0] == GLES20.GL_FALSE)
        {
            return false;
        }
        return true;
    }
    private void printShaderCompilingError(int shaderHandle, ShaderType type) {
        int[] maxLenth = new int[1];
        GLES20.glGetShaderiv(shaderHandle, GLES20.GL_INFO_LOG_LENGTH, maxLenth, 0);

        String infoLog = GLES20.glGetShaderInfoLog(shaderHandle);
        Log.e("SHADER", "SHADER::SHADER_COMPILE::COMPILE_FAILED::TYPE=" + type.toString());
        Log.e("SHADER",  infoLog);
    }

    private void linkProgram() {
        GLES20.glLinkProgram(programHandle);
        if(hasLinked())
        {
            printLinkInfoLog();
            detachAllShaders();
        } else
        {
            GLES20.glDeleteProgram(programHandle);
            deleteAllShaders();
        }
    }
    private boolean hasLinked() {
        int[] isLinked = new int[1];
        GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, isLinked, 0);
        if(isLinked[0] == GLES20.GL_FALSE)
        {
            return false;
        }
        return true;
    }
    private void printLinkInfoLog() {
        int[] maxLength = new int[]{0};
        GLES20.glGetProgramiv(programHandle, GLES20.GL_INFO_LOG_LENGTH, maxLength, 0);

        String infoLog = GLES20.glGetProgramInfoLog(programHandle);

        Log.i("SHADER", "SHADER::LINKING::LINKING_ERROR ");
        Log.i("SHADER", infoLog);
    }
    private void detachAllShaders() {
        for(Integer handle : shaderHandles.values()) {
            GLES20.glDeleteShader(handle);
        }
    }

    private void bindAttributeLocations() {
        GLES20.glBindAttribLocation(programHandle, 0, POSITION_ATTR_NAME);
        GLES20.glBindAttribLocation(programHandle, 1, COLOR_ATTR_NAME);
        GLES20.glBindAttribLocation(programHandle, 2, TEXTURE_ATTR_NAME);
    }

    private final void setUniformFloat2(String name, float x, float y) {
        int handle = getUniformHandle(name);
        GLES20.glUniform2f(handle, x, y);
    }
    private final void setUniformFloat3(String name, float x, float y, float z) {
        int handle = getUniformHandle(name);
        GLES20.glUniform3f(handle, x, y, z);
    }
    private final void setUniformFloat4(String name, float x, float y, float z, float w) {
        int handle = getUniformHandle(name);
        GLES20.glUniform4f(handle, x, y, z, w);
    }

    protected final void setUniformFloat1(String name, float x) {
        int handle = getUniformHandle(name);
        GLES20.glUniform1f(handle, x);
    }
    protected final void setUniformMat4(String name, float[] matrix4) {
        int handle = getUniformHandle(name);
        GLES20.glUniformMatrix4fv(handle, 1, false, matrix4, 0);
    }
    protected final void setUniformVector2(String name, Vector2 vector2) {
        setUniformFloat2(name, vector2.getX(), vector2.getY());
    }
    protected final void setUniformVector3(String name, Vector3 vector3) {
        setUniformFloat3(name, vector3.getX(), vector3.getY(), vector3.getZ());
    }
    protected final void setUniformColor(String name, RGBAColor color) {
        setUniformFloat4(name, color.r, color.g, color.b, color.a);
    }
    protected final void setUniformTexture(String name, Texture texture) {
        setUniformFloat1(name, texture.getSlot());
    }

    private int getUniformHandle(String name) {
        if (uniformHandles.containsKey(name)) {
            return uniformHandles.get(name);
        } else
        {
            int handle = GLES20.glGetUniformLocation(programHandle, "name");
            uniformHandles.put(name, handle);

            return handle;
        }
    }

    public final void use() {
        GLES20.glUseProgram(programHandle);
        bindAttributeLocations();
    }
    public final void unuse() {
        GLES20.glUseProgram(0);
    }
}
