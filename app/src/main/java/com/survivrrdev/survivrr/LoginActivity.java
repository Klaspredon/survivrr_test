package com.survivrrdev.survivrr;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.util.Log;
import android.view.View;

import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    private ConstraintLayout constraintLayout;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    private InputValidation inputValidation;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        constraintLayout = (ConstraintLayout) findViewById(R.id.ConstraintLayout1);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.TextInputLayout1);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.TextInputLayout2);
        textInputEditTextUsername = (TextInputEditText) findViewById(R.id.TextInputEditText1);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.TextInputEditText2);
        buttonLogin = (Button) findViewById(R.id.button);
        buttonRegister = (Button) findViewById(R.id.button2);

        dbHelper = new DBHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    public void login(View view){
        verifyFromSQLite();
    }

    public void register(View view){
        startActivity(new Intent(LoginActivity.this, Registration1.class));
    }

    private void verifyFromSQLite(){
        if(!inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername,
                getString(R.string.error_message_username))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword,
                textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }

        if(dbHelper.checkUserInfo(textInputEditTextUsername.getText().toString().trim(),
                textInputEditTextPassword.getText().toString().trim())){
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("USERNAME", textInputEditTextUsername.getText().toString().trim());
            emptyInputEditText();
            startActivity(intent);
        } else {
            Snackbar.make(constraintLayout, getString(R.string.error_valid_username_password),
                    Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextUsername.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
