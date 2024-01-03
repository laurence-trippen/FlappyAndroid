package com.smartelephantapps.flappyandroid.gles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import com.smartelephantapps.flappyandroid.game.FlappyGame;

public class FlappyGLSurfaceView extends GLSurfaceView implements View.OnTouchListener {

    private final FlappyGLRenderer renderer;

    public FlappyGLSurfaceView(Context context, FlappyGame game) {
        super(context);

        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(3);

        // Set Renderer object
        this.renderer = new FlappyGLRenderer(context, game);
        this.setRenderer(this.renderer);

        // Set Renderer mode
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                // TODO: Implement Input Handling
                break;
        }

        return false; // Return true to indicate that the event has been consume
    }
}
