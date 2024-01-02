package com.smartelephantapps.flappyandroid.utils;

import static android.opengl.GLES30.*;

import android.content.Context;

public final class ShaderUtils {

    private ShaderUtils() {}

    public static int load(Context context, int vertexResourceId, int fragmentResourceId) {
        String vert = FileUtils.loadAsStringFromResources(context, vertexResourceId);
        String frag = FileUtils.loadAsStringFromResources(context, fragmentResourceId);

        return create(vert, frag);
    }

    public static int create(String vert, String frag) {
        int program = glCreateProgram();

        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        glCompileShader(vertID);

        // TODO: Fix checking shader compilation status
        /*
        if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile vertex shader!");
            System.err.println(glGetShaderInfoLog(vertID));
            return -1;
        }
        */

        glCompileShader(fragID);

        // TODO: Fix checking shader compilation status
        /*
        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.println("Failed to compile fragment shader!");
            System.err.println(glGetShaderInfoLog(fragID));
            return -1;
        }
        */

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);

        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
    }
}
