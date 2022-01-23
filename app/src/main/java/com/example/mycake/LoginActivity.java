package com.example.mycake;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

  private Button btnLogin;
  private TextView tvRegister;
  private EditText etUsername, etPassword;
  private String usernameInput, passwordInput;
  private AlertDialog.Builder alertDialogBuilder;
  private ImageView backButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_login);

    alertDialogBuilder = new AlertDialog.Builder(this);

    initView();
    setData();
  }

  public void initView() {
    btnLogin = findViewById(R.id.btn_login);
    tvRegister = findViewById(R.id.tv_sign_up);
    etUsername = findViewById(R.id.et_username);
    etPassword = findViewById(R.id.et_password);
    backButton = findViewById(R.id.backButton);
  }

  private boolean validateUsername() {
    if (usernameInput.matches("")) {
      etUsername.setBackgroundResource(R.drawable.input_field_error);
      etUsername.setError("This field cannot be blank");
      return false;
    } else {
      etUsername.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  private boolean validatePassword() {
    if (passwordInput.matches("")) {
      etPassword.setBackgroundResource(R.drawable.input_field_error);
      etPassword.setError("This field cannot be blank");
      return false;
    } else {
      etPassword.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  public void setData() {
    btnLogin.setOnClickListener(view -> {

      // get data
      usernameInput = etUsername.getText().toString();
      passwordInput = etPassword.getText().toString();

      //action
//      sendDataViaIntent();
      loginUser();

    });

    tvRegister.setOnClickListener(view -> openRegisterView());

    backButton.setOnClickListener(view -> openWelcomeView());
  }

  private void loginUser() {
    if (!validateUsername() | !validatePassword()) {
      return;
    } else {
      isUser();
      Log.d("cek", "is user");
    }
  }

  private void isUser() {
    String linkFirebase = "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app";

    DatabaseReference reference = FirebaseDatabase.getInstance(linkFirebase).getReference("users");
    Log.i("cek", "cek username");

    Query query = reference.orderByChild("username").equalTo(usernameInput);

    query.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot snapshot) {

        //check user is exist/no
        if (snapshot.exists()) {

//          etUsername.setError(null);

          String passwordDB = snapshot.child(usernameInput).child("password").getValue(String.class);

          //check if user match with password
          if (passwordDB == null) {
          Log.i("cek pass", "passwordDB null");
            etPassword.setError("Wrong password");
            return;
          } else if (passwordDB.equals(passwordInput)) {

//            etUsername.setError(null);
            String emailFromDB = snapshot.child(usernameInput).child("email").getValue(String.class);
            String fullNameFromDB = snapshot.child(usernameInput).child("fullName").getValue(String.class);
            String passwordFromDB = snapshot.child(usernameInput).child("password").getValue(String.class);
            String phoneNumberFromDB = snapshot.child(usernameInput).child("phoneNumber").getValue(String.class);
            String usernameFromDB = snapshot.child(usernameInput).child("username").getValue(String.class);

            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            intent.putExtra("fullName", fullNameFromDB);
            intent.putExtra("username", usernameFromDB);
            intent.putExtra("email", emailFromDB);
            intent.putExtra("password", passwordFromDB);
            intent.putExtra("phoneNumber", phoneNumberFromDB);

            // log
            Log.i("fullName",fullNameFromDB);
            Log.i("username",usernameFromDB);
            Log.i("email",emailFromDB);
            Log.i("pass",passwordFromDB);
            Log.i("phone",phoneNumberFromDB);

            startActivity(intent);
          } else {
            etPassword.setError("Wrong password");
          }
        } else {
          etUsername.setError("No such User exists");
          etUsername.requestFocus();
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError error) {
        Toast.makeText(LoginActivity.this, "Firebase error", Toast.LENGTH_SHORT).show();
      }
    });

  }

  private void openRegisterView() {
    Intent intent = new Intent(this, SignUpActivity.class);
    startActivity(intent);
  }

  private void openWelcomeView() {
    Intent intent = new Intent(this, WelcomeActivity.class);
    startActivity(intent);
  }

  //  if back button pressed, do
  @Override
  public void onBackPressed() {
//    super.onBackPressed();
    Toast.makeText(LoginActivity.this, "Anda menekan tombol back", Toast.LENGTH_SHORT).show();

    alertDialogBuilder.setTitle("Tutup Aplikasi");
    alertDialogBuilder
      .setMessage("Apakah anda ingin menutup aplikasi?")
      .setCancelable(false)
      .setPositiveButton("Ya",
        (dialog, id) -> Toast.makeText(LoginActivity.this, "Anda menekan Ya", Toast.LENGTH_SHORT).show())
      .setNegativeButton("Tidak",
        (dialog, id) -> {
          dialog.cancel();
          Toast.makeText(LoginActivity.this, "Anda menekan Tidak", Toast.LENGTH_SHORT).show();
        })
      .create()
      .show();
  }

//  private void sendDataViaIntent(){
//    Intent intent = new Intent(LoginActivity.this, ShowLoginActivity.class);
//    intent.putExtra("username",username);
//    intent.putExtra("password",password);
//    startActivity(intent);
//  }
}