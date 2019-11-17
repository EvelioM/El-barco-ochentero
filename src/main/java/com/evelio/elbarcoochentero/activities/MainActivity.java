package com.evelio.elbarcoochentero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.evelio.elbarcoochentero.R;
import com.evelio.elbarcoochentero.control.ControlLogin;
import com.evelio.elbarcoochentero.database.Users;
import com.evelio.elbarcoochentero.listeners.OnLoginListener;

public class MainActivity extends AppCompatActivity {

    private ControlLogin controlLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Users users = new Users(this, "Users", null, 1);
        final SQLiteDatabase db = users.getWritableDatabase();

        controlLog = findViewById(R.id.loginControl);
        controlLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });
        controlLog.setOnLoginListener(new OnLoginListener(){
            @Override
            public void onLogin(String usr, String password){
                String Query = "Select * from Users where nick = \'" + usr + "\' and password = \'" + password + "\';";
                Cursor cursor = db.rawQuery(Query, null);

                if (cursor.getCount() == 1){
                    cursor.close();
                    db.close();
                    finish();
                    goLogged(usr);
                }

                else{
                    controlLog.setMessage(getResources().getString(R.string.accessFailed));
                    cursor.close();
                }

            }
        });
    }

    public void goRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    } //TODO This should return the user so yoy can close this activity

    public void goLogged(String nick){
        Intent intent = new Intent(this, MainLogged.class);
        intent.putExtra("nick",nick);
        startActivity(intent);
    }
}
