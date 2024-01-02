package com.smartelephantapps.flappyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.smartelephantapps.flappyandroid.gles.FlappyGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_main);

        this.glView = new FlappyGLSurfaceView(this);
        setContentView(this.glView);
    }
}
