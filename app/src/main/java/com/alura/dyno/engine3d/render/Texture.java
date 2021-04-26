package com.alura.dyno.engine3d.render;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Texture {
    private static boolean[] hasBoundTexture;
    private static int MAX_TEXTURE_SLOT = 0;
    public boolean isBound = false;
    int slot = -1;

    private int textureId;
    private int width;
    private int height;
    private int resourceId;

    public Texture(int resourceId, Context context) {
        generateTexture();
        this.resourceId = resourceId;
        if (textureId != 0) {
            final Bitmap bitmap = loadBitmap(resourceId, context);
            updateTexture(bitmap);
            bitmap.recycle();

            this.resourceId = resourceId;
        } else {
            throw new RuntimeException("Error loading texture");
        }
    }

    private void updateTexture(Bitmap bitmap) {
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    @NotNull
    private Bitmap loadBitmap(int imageResource, Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), imageResource, options);
        width = bitmap.getHeight();
        height = bitmap.getHeight();
        return bitmap;
    }

    private void generateTexture() {
        int[] textureIdArray = new int[1];
        GLES20.glGenTextures(1, textureIdArray, 0);

        textureId = textureIdArray[0];
    }

    public int id() {
        return textureId;
    }
    public int getSlot() {
        return slot;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public int getFirstFreeSlot() {
        for(int i = 0; i < MAX_TEXTURE_SLOT; i++) {
            if(hasBoundTexture[i] == false) {
                return i;
            }
        }
        return -1;
    }
    public static void setMaxTextureUnits(int maxTextureUnits) {
        MAX_TEXTURE_SLOT = maxTextureUnits - 1;

        hasBoundTexture = new boolean[maxTextureUnits];
        Arrays.fill(hasBoundTexture, false);
    }
    public void bind() {
        int firstFreeSlot = getFirstFreeSlot();

        if(!isBound) {
            if (firstFreeSlot == -1) {
                throw new RuntimeException("NO FREE SLOTS " + firstFreeSlot + " > " + MAX_TEXTURE_SLOT);
            }

            this.slot = firstFreeSlot;
            hasBoundTexture[firstFreeSlot] = true;

            GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + slot);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);

            isBound = true;
        }
    }
    public void unbind() {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + slot);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        hasBoundTexture[slot] = false;
        slot = -1;
        isBound = false;
    }

    public int getResourceId() {
        return resourceId;
    }

}
