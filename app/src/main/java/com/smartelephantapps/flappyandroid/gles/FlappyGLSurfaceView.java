package com.smartelephantapps.flappyandroid.gles;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class FlappyGLSurfaceView extends GLSurfaceView {

    private final FlappyGLRenderer renderer;

    public FlappyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(3);

        // Set Renderer object
        this.renderer = new FlappyGLRenderer(context);
        this.setRenderer(this.renderer);

        // Set Renderer mode
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
