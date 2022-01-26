package com.example.mycake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity {

  private TextView tvFullName;
  private ImageView ivLogOut;
  private String fullName, username, password, email, phoneNumber;
  private RelativeLayout rlEditProfile;

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
    rlEditProfile = findViewById(R.id.btn_edit_profile);
  }

  private void setData(){
    Intent intentGet = getIntent();

    fullName = intentGet.getStringExtra("fullName");
    username = intentGet.getStringExtra("username");
    email = intentGet.getStringExtra("email");
    password = intentGet.getStringExtra("password");
    phoneNumber = intentGet.getStringExtra("phoneNumber");

    tvFullName.setText(fullName);

    rlEditProfile.setOnClickListener(view -> {
      Intent intent = new Intent(getApplicationContext(), UserEditActivity.class);

      intent.putExtra("fullName", fullName);
      intent.putExtra("email", email);
      intent.putExtra("password", password);
      intent.putExtra("phoneNumber", phoneNumber);

      startActivity(intent);
    });

    ivLogOut.setOnClickListener(view -> openLoginView());
  }

  private void openLoginView(){
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
  }
}