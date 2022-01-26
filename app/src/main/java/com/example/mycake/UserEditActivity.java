package com.example.mycake;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserEditActivity extends AppCompatActivity {

  private EditText etUsername, etFullName, etEmail, etPassword, etPhoneNumber;
  private String fullName, username, password, email, phoneNumber;

  public UserEditActivity() {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_edit);

    initView();
    setData();
  }

  private void setData() {

    // get intent
    Intent intent = getIntent();
    fullName = intent.getStringExtra("fullName");
    email = intent.getStringExtra("email");
    password = intent.getStringExtra("password");
    phoneNumber = intent.getStringExtra("phoneNumber");

    //set edit text
    etFullName.setText(fullName);
    etEmail.setText(email);
    etPassword.setText(password);
    etPhoneNumber.setText(phoneNumber);
  }

  private void initView() {
    etFullName = findViewById(R.id.et_full_name);
    etEmail = findViewById(R.id.et_email);
    etPassword = findViewById(R.id.et_password);
    etPhoneNumber = findViewById(R.id.et_phone_number);
  }
}