package com.nzy.gameover;

import android.opengl.GLES20;
import android.util.Log;

public class ShaderHelper {

    private static final String TAG = "mediaRender";

    public static int buildProgram(String vertexSourceCode, String fragmentSourceCode) {
        int program;
        //compile
        int vertexShader = compileVertexShader(vertexSourceCode);
        int fragmentShader = compileFragmentShader(fragmentSourceCode);
        //link
        program = linkProgram(vertexShader, fragmentShader);
        validateProgram(program);
        return program;
    }

    private static void validateProgram(int program) {
        GLES20.glValidateProgram(program);
        int[] validateStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        if (validateStatus[0] == 0) {
            Log.e(TAG, "Results of validating program : " + validateStatus[0]
                    + "\n Log : " + GLES20.glGetProgramInfoLog(program));
        }
    }

    private static int linkProgram(int vertexShader, int fragmentShader) {
        int program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        int[] linkStatus = new int[1];
        GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLES20.glDeleteProgram(program);
            Log.e(TAG, "Linking of program failed. Reason : \n" + GLES20.glGetProgramInfoLog(program));
        }
        return program;
    }

    private static int compileVertexShader(String shaderCode) {
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    private static int compileFragmentShader(String shaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderSourceCode) {
        int shaderId = GLES20.glCreateShader(type);
        String errInfo = "none";
        if (shaderId != 0) {
            GLES20.glShaderSource(shaderId, shaderSourceCode);
            GLES20.glCompileShader(shaderId);
            int[] compileStatus = new int[1];
            GLES20.glGetShaderiv(shaderId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
            if (compileStatus[0] == 0) {
                errInfo = GLES20.glGetShaderInfoLog(shaderId);
                GLES20.glDeleteShader(shaderId);
            }
        }
        if (shaderId == 0) {
            Log.e(TAG, "could not create new shader. Reason : \n" + errInfo);
        }
        return shaderId;
    }
}
