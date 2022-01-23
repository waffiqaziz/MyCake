package com.example.mycake;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

  //Variables
  Animation topAnim, bottomAnim;
  TextView textView;
  ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_splash);

    setAnimation();

    new Handler(Looper.getMainLooper()).postDelayed(() -> {
      Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
      startActivity(intent);
      finish();
    }, 3000);
  }

  public void setAnimation(){
    topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
    bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

    textView = findViewById(R.id.text_view);
    imageView = findViewById(R.id.image_view);

    textView.setAnimation(topAnim);
    imageView.setAnimation(bottomAnim);
  }

}