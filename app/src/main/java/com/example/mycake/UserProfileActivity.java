package com.example.mycake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

  private TextView tvFullName;
  private ImageView ivLogOut;
  String fullName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_profile);

    initView();
    setData();
  }

  private void initView(){
    tvFullName = findViewById(R.id.fullName);
    ivLogOut = findViewById(R.id.logOut);
  }

  private void setData(){
    Intent intent = getIntent();
    fullName = intent.getStringExtra("fullName");

    tvFullName.setText(fullName);

    ivLogOut.setOnClickListener(view -> openLoginView());
  }

  private void openLoginView(){
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
  }
}