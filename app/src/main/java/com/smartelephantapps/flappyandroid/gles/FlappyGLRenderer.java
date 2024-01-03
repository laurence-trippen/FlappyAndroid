package com.smartelephantapps.flappyandroid.gles;

import static android.opengl.GLES30.*;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.smartelephantapps.flappyandroid.game.FlappyGame;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class FlappyGLRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    private final FlappyGame game;

    public FlappyGLRenderer(Context context, FlappyGame game) {
        this.context = context;
        this.game = game;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        // Flappy specific caps
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        this.game.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        this.game.tick();
    }
}
