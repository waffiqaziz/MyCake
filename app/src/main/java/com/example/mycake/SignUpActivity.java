package com.example.mycake;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class SignUpActivity extends AppCompatActivity {

  // Variabel
  private AlertDialog.Builder alertDialogBuilder;
  private Button btnSignUp;

  private EditText etFullName, etUsername, etEmail, etPassword, etPassword2, etPhone;
  private String fullName, username, email, password, password2, codeCountry, phoneNumber;
  private TextView tvLogin, tvNeedHelp;
  private CountryCodePicker pickCode;

  // firebase
  private FirebaseDatabase database;
  private DatabaseReference myRef;
  private final String linkFirebase = "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_sign_up);

    initView();
    setData();
  }

  private void initView() {
    btnSignUp   = findViewById(R.id.btn_create);
    tvLogin     = findViewById(R.id.tv_login);
    tvNeedHelp  = findViewById(R.id.tv_need_help);
    etFullName  = findViewById(R.id.et_full_name);
    etUsername  = findViewById(R.id.et_username);
    etEmail     = findViewById(R.id.et_email);
    pickCode    = findViewById(R.id.code_country);
    etPhone     = findViewById(R.id.et_number);
    etPassword  = findViewById(R.id.et_password);
    etPassword2 = findViewById(R.id.et_confirm_password);
  }

  private void getData() {
    fullName    = etFullName.getText().toString();
    username    = etUsername.getText().toString();
    email       = etEmail.getText().toString();
    password    = etPassword.getText().toString();
    password2   = etPassword2.getText().toString();
    phoneNumber = etPhone.getText().toString();
    codeCountry = pickCode.getSelectedCountryCodeWithPlus();
  }

  private void setData() {
    tvLogin.setOnClickListener(view -> openLoginView());

    btnSignUp.setOnClickListener(view -> {
      //get all input data
      getData();

      //check input
      if (!validateInput()) return;
      else {
        //showOnDialog()

        // Send data to firebase
        database = FirebaseDatabase.getInstance(linkFirebase);
        myRef = database.getReference("users");
        UserHelperClass userHelperClass = new UserHelperClass(fullName, username, password, password2, phoneNumber, email);
        myRef.child(username).setValue(userHelperClass)
          .addOnSuccessListener(aVoid -> {
            Toast.makeText(SignUpActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
            openLoginView();
          })
          .addOnFailureListener(e -> Toast.makeText(SignUpActivity.this, "Failed", Toast.LENGTH_SHORT).show());
      }
    });
  }

  private void openLoginView() {
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity(intent);
  }

  private boolean validateFullName(){
    if(fullName.matches("")) {
      etFullName.setBackgroundResource(R.drawable.input_field_error);
      etFullName.setError("This field cannot be blank");
      return false;
    }else {
      etFullName.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  private boolean validateUsername(){
    if(username.matches("")) {
      etUsername.setBackgroundResource(R.drawable.input_field_error);
      etUsername.setError("This field cannot be blank");
      return false;
    } else if(username.length() >= 15) {
      etUsername.setBackgroundResource(R.drawable.input_field_error);
      etUsername.setError("Max length 15 character");
      return false;
    } else if(username.matches(String.valueOf(R.string.whiteSpaces))) {
      etUsername.setBackgroundResource(R.drawable.input_field_error);
      etUsername.setError("White spaces not allowed");
      return false;
    } else {
      etUsername.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  private boolean validateEmail(){
    if(email.matches("")) {
      etEmail.setBackgroundResource(R.drawable.input_field_error);
      etEmail.setError("This field cannot be blank");
      return false;
//    } else if(!email.matches(getString(R.string.emailPattern))) {
    } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
      etEmail.setBackgroundResource(R.drawable.input_field_error);
      etEmail.setError("Invalid email address");
      return false;
    } else if(email.matches(getString(R.string.whiteSpaces))) {
      etEmail.setBackgroundResource(R.drawable.input_field_error);
      etEmail.setError("White spaces not allowed");
      return false;
    } else {
      etEmail.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  private boolean validatePassword(){
    String passwordPattern = "^(?=.*[0-9])"
                            + "(?=.*[a-z])(?=.*[A-Z])"
                            + "(?=.*[@#$%^&+=])"
                            + "(?=\\S+$).{8,20}$";

    if(password.matches("")) {
      etPassword.setBackgroundResource(R.drawable.input_field_error);
      etPassword.setError("This field cannot be blank");
      return false;
    } else if(!password.matches(passwordPattern)){
      etPassword.setBackgroundResource(R.drawable.input_field_error);
      etPassword.setError("Password is too weak");
      return false;
    } else {
      etPassword.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  private boolean validatePassword2(){
    //cek password
    Log.i("password",password);
    Log.i("password2",password2);

    if(password2.matches("")) {
      etPassword2.setBackgroundResource(R.drawable.input_field_error);
      etPassword2.setError("This field cannot be blank");
      return false;
    } else if(!password2.equals(password)){
      etPassword2.setBackgroundResource(R.drawable.input_field_error);
      etPassword2.setError("Password not same");
      return false;
    }else {
      etPassword2.setBackgroundResource(R.drawable.input_field);
      return true;
    }
  }

  private boolean validatePhoneNumber(){
    if(phoneNumber.matches("")) {
      etPhone.setBackgroundResource(R.drawable.input_field_error);
      etPhone.setError("This field cannot be blank");
      return false;
    }else {
      etPhone.setBackgroundResource(R.drawable.input_field);

      //concat phone number and code country
      phoneNumber = codeCountry + phoneNumber;
      return true;
    }
  }

  private boolean validateInput() {
    return !(!validateFullName()
      | !validateUsername()
      | !validateEmail()
      | !validatePhoneNumber()
      | !validatePassword()
      | !validatePassword2());
  }

  // show on dialog
//  private void showOnDialog(){
//    alertDialogBuilder = new AlertDialog.Builder(this);
//    alertDialogBuilder
//      .setTitle("Number Phone")
//      .setMessage("code country : " + codeCountry + "\n" +
//        "phone : " + phoneNumber + "\n" +
//        "full name : " + fullName + "\n" +
//        "user name : " + username + "\n" +
//        "pass : " + password + "\n" +
//        "pass2 : " + password2 + "\n" +
//        "is that correct?")
//      .setCancelable(false)
//      .setPositiveButton("Yes",
//        (dialog, id) -> Toast.makeText(SignUpActivity.this, "You pressed yes", Toast.LENGTH_SHORT).show())
//      .setNegativeButton("No",
//        (dialog, id) -> {
//          dialog.cancel();
//          Toast.makeText(SignUpActivity.this, "You pressed no", Toast.LENGTH_SHORT).show();
//        })
//      .create()
//      .show();
//  }
}