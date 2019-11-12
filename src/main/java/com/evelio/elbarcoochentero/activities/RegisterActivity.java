package com.evelio.elbarcoochentero.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evelio.elbarcoochentero.R;
import com.evelio.elbarcoochentero.database.Users;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonUser = findViewById(R.id.buttonSubmitRegister);
        final EditText textUser = findViewById(R.id.editTextUserReg);
        final EditText textPass = findViewById(R.id.editTextPasswordReg);
        final EditText textPass2 = findViewById(R.id.editTextPasswordReg2);
        final TextView error = findViewById(R.id.textViewError);


        Users users = new Users(this, "Users", null, 1);
        final SQLiteDatabase db = users.getWritableDatabase();

        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(textUser.getText().toString()) ||
                        TextUtils.isEmpty(textPass.getText().toString()) ||
                        TextUtils.isEmpty(textPass2.getText().toString())){
                    error.setText(getResources().getString(R.string.unfilled));
                }else if(! textPass.getText().toString().equals(textPass2.getText().toString())){
                    error.setText(getResources().getString(R.string.differentPass));
                }else{
                    ContentValues values=new ContentValues();
                    values.put("nick", textUser.getText().toString());
                    values.put("password", textPass.getText().toString());

                    db.insert("Users", null, values);
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Bienvenido " + textUser.getText().toString(), Toast.LENGTH_LONG);
                    toast1.show();

                    db.close();
                    finish();
                }
            }
        });

    }


}
