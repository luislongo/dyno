package com.alura.dyno.engine3d.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

public class Texture {
    public static int FIRST_FREE_SLOT = 0;
    int slot = -1;
    private int[] textureId = new int[1];
    private int imageSize;

    public Texture(int imageResource, Context context) {
        GLES20.glGenTextures(1, textureId, 0);

        if (textureId[0] != 0) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;

            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageResource, options);
            imageSize = bitmap.getHeight();

            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            bitmap.recycle();
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        } else {
            throw new RuntimeException("Error loading texture");
        }
    }

    public int id() {
        return textureId[0];
    }

    public int getSlot() {
        return slot;
    }

    public void bind(int slot) {
        this.slot = slot;

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + slot);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId[0]);
    }

    public int getImageSize() {
        return imageSize;
    }

    public void unbind() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + slot);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        slot = -1;
    }
}
