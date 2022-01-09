package com.example.mycake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
  private TextView registerTv;
  private Button buttonLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    initView();
    gotoRegister();
    gotoLogin();
  }

  public void initView(){
    registerTv = findViewById(R.id.tv_register);
    buttonLogin = findViewById(R.id.btn_login);
  }

  public void gotoRegister(){
    registerTv.setOnClickListener(v -> {
      Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
      startActivity(intent);
    });
  }

  public void gotoLogin(){
    buttonLogin.setOnClickListener(v -> {
      Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
      startActivity(intent);
    });
  }

}