package com.survivrrdev.survivrr;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

public class Registration1 extends AppCompatActivity {
    private final AppCompatActivity activity = Registration1.this;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private EditText editTextContactName;
    private EditText editTextContactPhone;
    private EditText editTextContactAddress;
    private Button buttonCancel;
    private Button buttonSave;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);

        editTextUsername = (EditText) findViewById(R.id.editText33);
        editTextPassword = (EditText) findViewById(R.id.editText34);
        editTextName = (EditText) findViewById(R.id.editText35);
        editTextPhone = (EditText) findViewById(R.id.editText37);
        editTextAddress = (EditText) findViewById(R.id.editText38);
        editTextContactName = (EditText) findViewById(R.id.editText39);
        editTextContactPhone = (EditText) findViewById(R.id.editText40);
        editTextContactAddress = (EditText) findViewById(R.id.editText41);
        buttonCancel = (Button) findViewById(R.id.button5);
        buttonSave = (Button) findViewById(R.id.button6);

        dbHelper = new DBHelper(Registration1.this);
    }

    @Override
    public void onBackPressed(){}

    public void cancelRegister(View view){
        startActivity(new Intent(Registration1.this, LoginActivity.class));
    }

    public void saveRegister(View view){
        saveToSQLite();
    }

    private void saveToSQLite(){
        dbHelper.insertUserInfo(editTextUsername.getText().toString().trim(),
                editTextPassword.getText().toString().trim(),
                editTextName.getText().toString().trim(),
                editTextPhone.getText().toString().trim(),
                editTextAddress.getText().toString().trim(),
                null,
                null,
                editTextContactName.getText().toString().trim(),
                editTextContactPhone.getText().toString().trim(),
                editTextContactAddress.getText().toString().trim()
                );
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
    }
}
