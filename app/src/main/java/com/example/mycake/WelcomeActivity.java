package com.example.mycake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
  private TextView tvSignUp;
  private Button buttonLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_welcome);

    initView();
    gotoSignUp();
    gotoLogin();
  }

  public void initView(){
    tvSignUp = findViewById(R.id.tv_sign_up);
    buttonLogin = findViewById(R.id.btn_login);
  }

  public void gotoSignUp(){
    tvSignUp.setOnClickListener(v -> {
      Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
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