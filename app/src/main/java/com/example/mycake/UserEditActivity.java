package com.example.mycake;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserEditActivity extends AppCompatActivity {

  private EditText etUsername, etFullName, etEmail, etPassword, etPhoneNumber;
  private String fullName, username, password, email, phoneNumber;
  private Button btnUpdate;
  private DatabaseReference reference;

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
    username = intent.getStringExtra("username");
    fullName = intent.getStringExtra("fullName");
    email = intent.getStringExtra("email");
    password = intent.getStringExtra("password");
    phoneNumber = intent.getStringExtra("phoneNumber");

    //set edit text
    etFullName.setText(fullName);
    etEmail.setText(email);
    etPassword.setText(password);
    etPhoneNumber.setText(phoneNumber);

    btnUpdate.setOnClickListener(view -> {
      String linkFirebase = "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app";
      reference = FirebaseDatabase.getInstance(linkFirebase).getReference("users");

      updateDataUser();
    });
  }

  private void initView() {
    etFullName = findViewById(R.id.et_full_name);
    etEmail = findViewById(R.id.et_email);
    etPassword = findViewById(R.id.et_password_update);
    etPhoneNumber = findViewById(R.id.et_phone_number);
    btnUpdate = findViewById(R.id.btn_update);
  }

  private void updateDataUser() {
    if (isFullNameChanged() | isPasswordChanged()) {
      Toast.makeText(UserEditActivity.this, "Data has been update", Toast.LENGTH_SHORT).show();
//      gotoUserProfile();
    } else {
      Toast.makeText(UserEditActivity.this, "Data is same cannot update", Toast.LENGTH_SHORT).show();
    }
  }

  private boolean isFullNameChanged() {
    if (!fullName.equals(etFullName.getText().toString())) {
      reference.child(username).child("fullName").setValue(etFullName.getText().toString());
      return true;
    } else {
      return true;
    }
  }

  private boolean isPasswordChanged() {
    if (!password.equals(etPassword.getText().toString())) {
      reference.child(username).child("password").setValue(etPassword.getText().toString());
      return true;
    } else {
      return false;
    }
  }

  private void gotoUserProfile(){
    Intent intent = new Intent(this, UserProfileActivity.class);

    startActivity(intent);
  }

}