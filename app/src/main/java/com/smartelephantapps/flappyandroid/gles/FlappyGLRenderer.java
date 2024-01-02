package com.smartelephantapps.flappyandroid.gles;

import static android.opengl.GLES30.*;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.smartelephantapps.flappyandroid.graphics.Shader;
import com.smartelephantapps.flappyandroid.maths.Matrix4f;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FlappyGLRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    public FlappyGLRenderer(Context context) {
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

        // Flappy specific caps
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        Shader.loadAll(this.context);

        Matrix4f projectionMatrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * 9.0f / 16.0f, 10.0f * 9.0f / 16.0f, -1.0f, 1.0f);

        Shader.BG.setUniformMat4f("pr_matrix", projectionMatrix);
        Shader.BG.setUniform1i("tex", 1);

        Shader.BIRD.setUniformMat4f("pr_matrix", projectionMatrix);
        Shader.BIRD.setUniform1i("tex", 1);

        Shader.PIPE.setUniformMat4f("pr_matrix", projectionMatrix);
        Shader.PIPE.setUniform1i("tex", 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);
    }
}
