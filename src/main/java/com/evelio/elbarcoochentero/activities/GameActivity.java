package com.evelio.elbarcoochentero.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.evelio.elbarcoochentero.game.views.GameView;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this, this));
    }

    public void returnWin(){
        Intent intencion = new Intent();
        intencion.putExtra("resultado", "WIN");
        setResult(Activity.RESULT_OK, intencion);
        finish();
    }

    public void returnLose(){
        Intent intencion = new Intent();
        intencion.putExtra("resultado", "LOSE");
        setResult(Activity.RESULT_OK, intencion);
        finish();
    }
}
