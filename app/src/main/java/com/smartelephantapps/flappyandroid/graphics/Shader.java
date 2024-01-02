package com.smartelephantapps.flappyandroid.graphics;

import android.content.Context;

import com.smartelephantapps.flappyandroid.R;
import com.smartelephantapps.flappyandroid.utils.ShaderUtils;

public class Shader {

    public static Shader BG, BIRD, PIPE, FADE;

    private final int ID;

    public Shader(Context context, int vertexResourceId, int fragmentResourceId) {
        this.ID = ShaderUtils.load(context, vertexResourceId, fragmentResourceId);
    }

    public static void loadAll(Context context) {
        BG = new Shader(context, R.raw.bg_vert, R.raw.bg_frag);
        BIRD = new Shader(context, R.raw.bird_vert, R.raw.bird_frag);
        PIPE = new Shader(context, R.raw.pipe_vert, R.raw.pipe_frag);
        FADE = new Shader(context, R.raw.fade_vert, R.raw.fade_frag);
    }
}
