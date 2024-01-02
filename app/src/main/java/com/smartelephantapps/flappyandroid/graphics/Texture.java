package com.smartelephantapps.flappyandroid.graphics;

import static android.opengl.GLES30.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

public class Texture {

    private int textureHandle[] = new int[1];

    public Texture(Context context, int resourceId) {
        load(context, resourceId);
    }

    private void load(Context context, int resourceId) {
        glGenTextures(1, textureHandle, 0);

        if (textureHandle[0] == 0) {
            Log.e("Texture Class", "Failed creating texture!");
            return;
        }

        glBindTexture(GL_TEXTURE_2D, textureHandle[0]);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);

        // Original just for reference
        // glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, BufferUtils.createIntBuffer(data));

        GLUtils.texImage2D(GL_TEXTURE_2D, 0, GL_RGBA, bitmap, 0);

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, textureHandle[0]);
    }

    public void unbind() {
        glBindTexture(GL_TEXTURE_2D, 0);
    }
}
