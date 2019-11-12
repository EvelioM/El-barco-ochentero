package com.evelio.elbarcoochentero.control;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.evelio.elbarcoochentero.R;
import com.evelio.elbarcoochentero.listeners.OnLoginListener;

public class ControlLogin extends LinearLayout {
    private EditText userText;
    private EditText passwordText;
    private TextView messageLabel;
    private Button loginButton;
    private Button registerButton;
    private OnLoginListener onloginlistener;

    public ControlLogin(Context context){
        super(context);
        initialize();
    }
    public ControlLogin(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void assignEvents(){
        loginButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                onloginlistener.onLogin(userText.getText().toString(), passwordText.getText().toString());
            }
        });
    }

    private void initialize(){
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li=(LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.control_login, this, true);

        userText = findViewById(R.id.editTextUser);
        passwordText =findViewById(R.id.editTextPass);
        loginButton =findViewById(R.id.buttonLog);
        registerButton=findViewById(R.id.buttonRegister);
        messageLabel=findViewById(R.id.textViewMessage);

        assignEvents();
    }



    public void setMessage(String Message){
        messageLabel.setText(Message);
    }

    public void setOnLoginListener(OnLoginListener oll){
        onloginlistener=oll;
    }

    public void setOnClickListener(OnClickListener ocl){registerButton.setOnClickListener(ocl);}
}