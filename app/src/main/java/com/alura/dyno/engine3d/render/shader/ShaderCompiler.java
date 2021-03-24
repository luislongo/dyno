package com.alura.dyno.engine3d.render.shader;

import android.opengl.GLES20;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ShaderCompiler {
    HashMap<ShaderType, Integer> shaderHandles;
    int programHandle;

    public ShaderCompiler() {
        shaderHandles = new HashMap<>();
    }

    public int compile() {
        programHandle = createProgram();
        attachAllShaders();
        linkProgram();

        return programHandle;
    }
    public void putShader(@NotNull ShaderSource source) {
        int shaderHandle = createShader(source.getType());
        loadShaderSourceIntoGPU(source.getSource(), shaderHandle);

        compileShader(shaderHandle);

        if (!hasCompiled(shaderHandle)) {
            printShaderCompilingError(shaderHandle, source.getType());
            GLES20.glDeleteShader(shaderHandle);
        } else {
            shaderHandles.put(source.getType(), shaderHandle);
        }
    }

    private int createProgram() {
        return GLES20.glCreateProgram();
    }
    private void attachAllShaders() {
        for (int shaderHandle : shaderHandles.values()) {
            attachShaderToProgram(shaderHandle);
        }
    }
    private void attachShaderToProgram(int shaderHandle) {
        GLES20.glAttachShader(programHandle, shaderHandle);
    }
    private void linkProgram() {
        GLES20.glLinkProgram(programHandle);

        if (!hasLinked()) {
            printLinkInfoLog();
            GLES20.glDeleteProgram(programHandle);
            deleteAllShaders();
        }
    }
    private boolean hasLinked() {
        int[] isLinked = new int[1];
        GLES20.glGetProgramiv(programHandle, GLES20.GL_LINK_STATUS, isLinked, 0);
        if (isLinked[0] == GLES20.GL_FALSE) {
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
    private void deleteAllShaders() {
        for (Integer handle : shaderHandles.values()) {
            GLES20.glDeleteShader(handle);
        }
    }

    private int createShader(ShaderType type) {
        return GLES20.glCreateShader(type.type);
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
        if (isCompiled[0] == GLES20.GL_FALSE) {
            return false;
        }
        return true;
    }
    private void printShaderCompilingError(int shaderHandle, ShaderType type) {
        int[] maxLenth = new int[128];
        GLES20.glGetShaderiv(shaderHandle, GLES20.GL_INFO_LOG_LENGTH, maxLenth, 0);

        String infoLog = GLES20.glGetShaderInfoLog(shaderHandle);
        Log.e("SHADER", "SHADER::SHADER_COMPILE::COMPILE_FAILED::TYPE=" + type.toString());
        Log.e("SHADER", infoLog);
    }
}