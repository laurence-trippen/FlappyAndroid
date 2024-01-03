package com.smartelephantapps.flappyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.smartelephantapps.flappyandroid.game.FlappyGame;
import com.smartelephantapps.flappyandroid.gles.FlappyGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glView;

    private FlappyGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.game = new FlappyGame(this);

        this.glView = new FlappyGLSurfaceView(this, game);
        setContentView(this.glView);
    }
}
