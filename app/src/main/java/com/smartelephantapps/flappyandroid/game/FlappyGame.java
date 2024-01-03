package com.smartelephantapps.flappyandroid.game;

import static android.opengl.GLES30.*;

import android.content.Context;

import com.smartelephantapps.flappyandroid.graphics.Shader;
import com.smartelephantapps.flappyandroid.maths.Matrix4f;
import com.smartelephantapps.flappyandroid.utils.ScreenUtils;

public class FlappyGame {

    public static final float STANDARD_ASPECT_RATIO = 9.0f / 16.0f;

    public static float ASPECT_RATIO = 0f;

    private final Context context;

    private Level level;

    private long lastTime = System.nanoTime();
    private double delta = 0.0;
    private double ns = 1000000000.0 / 60.0;
    private long timer = System.currentTimeMillis();
    private int updates = 0;
    private int frames = 0;

    public FlappyGame(Context context) {
        this.context = context;

        final float deviceAspectRatio = ScreenUtils.getAspectRatio(context);

        FlappyGame.ASPECT_RATIO = deviceAspectRatio <= 0f ? FlappyGame.STANDARD_ASPECT_RATIO : deviceAspectRatio;
    }

    public void init() {
        Shader.loadAll(this.context);

        Matrix4f projectionMatrix = Matrix4f.orthographic(-10.0f, 10.0f, -10.0f * FlappyGame.ASPECT_RATIO, 10.0f * FlappyGame.ASPECT_RATIO, -1.0f, 1.0f);

        Shader.BG.setUniformMat4f("pr_matrix", projectionMatrix);
        Shader.BG.setUniform1i("tex", 1);

        Shader.BIRD.setUniformMat4f("pr_matrix", projectionMatrix);
        Shader.BIRD.setUniform1i("tex", 1);

        Shader.PIPE.setUniformMat4f("pr_matrix", projectionMatrix);
        Shader.PIPE.setUniform1i("tex", 1);

        this.level = new Level(this.context);
    }

    public void tick() {
        long now = System.nanoTime();
        delta += (now - lastTime) / ns;
        lastTime = now;

        if (delta >= 1.0) {
            update();
            updates++;
            delta--;
        }

        render();

        frames++;

        if (System.currentTimeMillis() - timer > 1000) {
            timer += 1000;
            System.out.println(updates + " ups, " + frames + " fps");
            updates = 0;
            frames = 0;
        }

        // TODO: needed?
        /*
        if (glfwWindowShouldClose(window) == GL_TRUE)
            running = false;
         */
    }

    private void update() {
        level.update();

        if (level.isGameOver()) {
            level = new Level(this.context);
        }
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        level.render();

        // TODO: Implement error checking
        /*
        int error = glGetError();
		if (error != GL_NO_ERROR)
			System.out.println(error);
        */

        // TODO: Check if swapBuffers() is needed
    }
}
