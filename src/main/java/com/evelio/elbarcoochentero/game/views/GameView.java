package com.evelio.elbarcoochentero.game.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.evelio.elbarcoochentero.R;
import com.evelio.elbarcoochentero.activities.GameActivity;
import com.evelio.elbarcoochentero.game.threads.MainThread;
import com.evelio.elbarcoochentero.game.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.preference.PreferenceManager;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public MainThread thread;
    private CharacterSprite characterSprite;
    private Sprite backSprite;
    private List<Sprite> enemies;
    private LifeSprite liveSprite;
    private long time = 0;
    Random r;
    private int lives = 3;
    private GameActivity gameActivity;
    private  MediaPlayer mp;

    public GameView(Context context, GameActivity ga) {
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;
        this.gameActivity = ga;

        Bitmap preimage = BitmapFactory.decodeResource(getResources(), R.drawable.narajito);

        float aspectRatio = preimage.getWidth() / (float) preimage.getHeight();
        int width = 300;
        int height = Math.round(width / aspectRatio);

        Constants.ENEMY_SPRITE = Bitmap.createScaledBitmap(preimage, width, height, false);

        preimage = BitmapFactory.decodeResource(getResources(), R.drawable.disco);

        aspectRatio = preimage.getWidth() / (float) preimage.getHeight();
        width = 200;
        height = Math.round(width / aspectRatio);

        Constants.OBSTACLE_SPRITE = Bitmap.createScaledBitmap(preimage, width, height, false);

        preimage = BitmapFactory.decodeResource(getResources(), R.drawable.heart);

        aspectRatio = preimage.getWidth() / (float) preimage.getHeight();
        width = 50;
        height = Math.round(width / aspectRatio);

        Constants.HEART_SPRITE = Bitmap.createScaledBitmap(preimage, width, height, false);

        r = new Random();
        enemies = new ArrayList<>();

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Constants.INIT_TIME = System.currentTimeMillis();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Constants.CURRENT_CONTEXT);

        if(preferences.getBoolean("Music",false)){
            mp= MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.music);
            mp.start();
            mp.setLooping(true);

        }

        characterSprite = new CharacterSprite();
        backSprite = new BackgroundSprite();
        enemies.clear();

        time = 0;
        lives = 5;

        liveSprite = new LifeSprite(25, lives - 1);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        if(mp != null){
            mp.stop();
        }
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        backSprite.update();

        Sprite delete = null;
        for(Sprite enemy : enemies){
            enemy.update();
            if(characterSprite.collided(enemy)){
                delete = enemy;
                lives--;
                liveSprite.loseLife(lives);
            }
        }
        enemies.remove(delete);

        if(time % 170 == 0){
            enemies.add(new ObstacleSprite(r.nextInt() % Constants.SCREEN_WIDTH));
        }

        if(time % 290 == 0){
            enemies.add(new EnemySprite(r.nextInt(),r.nextInt(Constants.SCREEN_WIDTH)));
        }

        characterSprite.update();
        time++;

        if(lives == 0){ // lose condition
            gameActivity.returnLose();
            gameActivity.finish();
        }

        if(time > 3000){ // win condition
            gameActivity.returnWin();
            gameActivity.finish();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        backSprite.draw(canvas);
        for(Sprite enemy : enemies){
            enemy.draw(canvas);
        }
        liveSprite.draw(canvas);
        characterSprite.draw(canvas);


    }

}
