package com.evelio.elbarcoochentero.game.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.evelio.elbarcoochentero.R;
import com.evelio.elbarcoochentero.game.util.Constants;

public class BackgroundSprite extends Sprite {

    private Bitmap image;
    private int x, y1, x2, y2;
    private float yVelocity = 0;
    private int height;
    private int width;

    public BackgroundSprite() {
        image = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT
                .getResources(), R.drawable.water);

        x = -10;
        y1 = -10;
        height = image.getHeight();
        width = image.getWidth();

        y2= -height;

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y1, null);
        canvas.drawBitmap(image, x2, y2, null);
    }

    @Override
    public void update() {
        y1 += 4;
        y2 += 4;

        if(y2 > 4){
            y1 = 0;
            y2 = -height;
        }
    }

    @Override
    public int getRightX() {
        return 0;
    }

    @Override
    public int getTopY() {
        return 0;
    }

    @Override
    public int getLeftX() {
        return 0;
    }

    @Override
    public int getBotY() {
        return 0;
    }
}
