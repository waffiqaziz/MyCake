package com.example.mycake;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

  private Button btnLogin;
  private TextView tvRegister;
  private EditText etUsername, etPassword;
  private String username, password;
  private AlertDialog.Builder alertDialogBuilder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    alertDialogBuilder = new AlertDialog.Builder(this);

    initView();
    setData();
  }

  public void initView(){
    btnLogin = findViewById(R.id.btn_login);
    tvRegister = findViewById(R.id.tv_register);
    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
  }

  public void setData(){
    btnLogin.setOnClickListener(view -> {

      username = etUsername.getText().toString();
      password = etPassword.getText().toString();

      //action
      Intent intent = new Intent(LoginActivity.this, ShowLoginActivity.class);
      intent.putExtra("username",username);
      intent.putExtra("password",password);
      startActivity(intent);
    });

    tvRegister.setOnClickListener(view -> openRegisterView());
  }

  public void openRegisterView(){
    Intent intent = new Intent(this, RegisterActivity.class);
    startActivity(intent);
  }

//  if back button pressed, do
  @Override
  public void onBackPressed() {
//    super.onBackPressed();
    Toast.makeText(LoginActivity.this,"Anda menekan tombol back", Toast.LENGTH_SHORT).show();

    alertDialogBuilder.setTitle("Tutup Aplikasi");
      alertDialogBuilder
        .setMessage("Apakah anda ingin menutup aplikasi?")
        .setCancelable(false)
        .setPositiveButton("Ya",
          (dialog, id) -> Toast.makeText(LoginActivity.this,"Anda menekan Ya", Toast.LENGTH_SHORT).show())
        .setNegativeButton("Tidak",
          (dialog, id) -> {
            dialog.cancel();
            Toast.makeText(LoginActivity.this,"Anda menekan Tidak", Toast.LENGTH_SHORT).show();
          })
        .create()
        .show();
  }

}