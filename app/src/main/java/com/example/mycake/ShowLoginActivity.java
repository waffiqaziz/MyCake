package com.example.mycake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowLoginActivity extends AppCompatActivity {

  private TextView tvUsername, tvPassword;
  private String username, password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_show_input_login);
    initView();
    setData();

    todoRegister();
  }

  public void initView(){
    tvUsername = findViewById(R.id.input_username);
    tvPassword = findViewById(R.id.input_password);
  }

  public void setData(){
    Intent intent = getIntent();
    username = intent.getStringExtra("username");
    password = intent.getStringExtra("password");

    tvUsername.setText(username);
    tvPassword.setText(password);
  }

  public void todoRegister(){

  }
}