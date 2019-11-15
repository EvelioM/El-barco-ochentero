package com.evelio.elbarcoochentero.game.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.evelio.elbarcoochentero.game.util.Constants;

public class LifeSprite extends Sprite {


    private Bitmap image;
    private int x;
    private LifeSprite nextLife = null;

    public LifeSprite(int x, int lives) {

        image = Constants.HEART_SPRITE;

        this.x = x;

        if(lives > 0){
            nextLife = new LifeSprite(x + 100, lives - 1);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, 25, null);
        if(nextLife != null){
            nextLife.draw(canvas);
        }
    }

    public void loseLife(int newLiveCount){
        if(newLiveCount > 1){
            nextLife.loseLife(newLiveCount - 1);
        }else{
            nextLife = null;
        }
    }

    @Override
    public void update() {}

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
