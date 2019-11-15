package com.evelio.elbarcoochentero.game.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.evelio.elbarcoochentero.R;
import com.evelio.elbarcoochentero.game.data.OrientationData;
import com.evelio.elbarcoochentero.game.util.Constants;

public class CharacterSprite extends Sprite{

    private Bitmap image;
    private int x, y;
    private float xVelocity = 0;
    private float yVelocity = 0;
    private int height;
    private int width;
    private OrientationData orientationData;


    public CharacterSprite() {
        Bitmap preimage = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT
                .getResources(), R.drawable.character);

        float aspectRatio = preimage.getWidth() /
                (float) preimage.getHeight();
        width = 200;
        height = Math.round(width / aspectRatio);

        image = Bitmap.createScaledBitmap(
                preimage, width, height, false);
        x = Constants.SCREEN_WIDTH / 2;
        y = (int)(Constants.SCREEN_HEIGHT * 0.7);
        height = image.getHeight();
        orientationData = new OrientationData();
        orientationData.register();
        orientationData.newGame();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    public void update(){

        if(orientationData.getOrientation() != null && orientationData.getStartOrientation() != null){
            float dRoll = orientationData.getOrientation()[2] - orientationData.getStartOrientation()[2];

            xVelocity = 2 * dRoll * Constants.SCREEN_WIDTH / 50f;
        }


        if (((x > Constants.SCREEN_WIDTH - image.getWidth()) && (xVelocity > 0))
                || ((x < 0) && (xVelocity < 0))) {
            xVelocity = 0;
        }

        x += Math.abs(xVelocity) > 5 ? xVelocity : 0;

    }

    @Override
    public int getRightX() {
        return x;
    }

    @Override
    public int getTopY() {
        return y;
    }

    @Override
    public int getLeftX() {
        return x;
    }

    @Override
    public int getBotY() {
        return y;
    }


    public boolean collided(Sprite s) {
        int realX = x + (width/2);
        int realY = y;

        if(s.getBotY() > realY && s.getTopY() < realY && s.getLeftX() < realX && s.getRightX() > realX ){
            return true;
        }else{
            return false;
        }
    }

}
