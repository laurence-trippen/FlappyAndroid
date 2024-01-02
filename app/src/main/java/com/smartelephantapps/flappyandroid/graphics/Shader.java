package com.smartelephantapps.flappyandroid.graphics;

import static android.opengl.GLES30.*;
import android.content.Context;

import com.smartelephantapps.flappyandroid.R;
import com.smartelephantapps.flappyandroid.maths.Matrix4f;
import com.smartelephantapps.flappyandroid.maths.Vector3f;
import com.smartelephantapps.flappyandroid.utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

public class Shader {

    // ------ STATIC PART --------

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    public static Shader BG, BIRD, PIPE, FADE;

    public static void loadAll(Context context) {
        BG = new Shader(context, R.raw.bg_vert, R.raw.bg_frag);
        BIRD = new Shader(context, R.raw.bird_vert, R.raw.bird_frag);
        PIPE = new Shader(context, R.raw.pipe_vert, R.raw.pipe_frag);
        FADE = new Shader(context, R.raw.fade_vert, R.raw.fade_frag);
    }

    // ------ CLASS PART --------

    private boolean enabled = false;

    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<>();

    public Shader(Context context, int vertexResourceId, int fragmentResourceId) {
        this.ID = ShaderUtils.load(context, vertexResourceId, fragmentResourceId);
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name))
            return locationCache.get(name);

        int result = glGetUniformLocation(ID, name);
        if (result == -1)
            System.err.println("Could not find uniform variable '" + name + "'!");
        else
            locationCache.put(name, result);
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniform3f(String name, Vector3f vector) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) enable();

        int count = matrix.elements.length;

        // TODO: Check if count is correct
        glUniformMatrix4fv(getUniform(name), 1, false, matrix.toFloatBuffer());

        // glUniformMatrix4(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }
}
