package com.survivrrdev.survivrr;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final AppCompatActivity activity = MainActivity.this;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private ScrollView scrollView;
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextAddress;
    private EditText editTextContactName;
    private EditText editTextContactPhone;
    private EditText editTextContactAddress;
    private TextView textView;
    private String username;
    private DBHelper dbHelper;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ScrollView) findViewById(R.id.ScrollView);
        textView = (TextView) findViewById(R.id.textView2);
        editTextName = (EditText) findViewById(R.id.editText);
        editTextPhone = (EditText) findViewById(R.id.editText2);
        editTextAddress = (EditText) findViewById(R.id.editText3);
        editTextContactName = (EditText) findViewById(R.id.editText4);
        editTextContactPhone = (EditText) findViewById(R.id.editText5);
        editTextContactAddress = (EditText) findViewById(R.id.editText6);

        dbHelper = new DBHelper(activity);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            username = extras.getString("USERNAME");
        }
        userInfo = dbHelper.getUserInfo(username);

        textView.setText(username);
        editTextName.setText(userInfo.getUserName());
        editTextPhone.setText(userInfo.getUserPhone());
        editTextAddress.setText(userInfo.getUserAddress());
        editTextContactName.setText(userInfo.getUserContactName());
        editTextContactPhone.setText(userInfo.getUserContactPhone());
        editTextContactAddress.setText(userInfo.getUserContactAddress());
    }

    public void updateProfile (View view){
        updateSQLite();
    }

    private void updateSQLite (){
        UserInfo updatedUserInfo = new UserInfo();
        updatedUserInfo.setUserName(editTextName.getText().toString().trim());
        updatedUserInfo.setUserPhone(editTextPhone.getText().toString().trim());
        updatedUserInfo.setUserAddress(editTextAddress.getText().toString().trim());
        updatedUserInfo.setUserContactName(editTextContactName.getText().toString().trim());
        updatedUserInfo.setUserContactPhone(editTextContactPhone.getText().toString().trim());
        updatedUserInfo.setUserContactAddress(editTextContactAddress.getText().toString().trim());
        dbHelper.updateUserInfo(username,updatedUserInfo);
        Snackbar.make(scrollView, "Profile Updated!",Snackbar.LENGTH_LONG).show();
    }

    public void goto_home (View view) {
        startActivity( new Intent(activity, HomeActivity.class) );
    }

    public void goto_faq (View view) {
//        Intent intent = new Intent(activity, HomeActivity.class);
//        startActivity(intent);
    }


    @Override
    public void onBackPressed(){}
}
