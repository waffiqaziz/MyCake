package com.example.mycake

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.hbb20.CountryCodePicker

class SignUpActivity : AppCompatActivity() {
    private var btnSignUp: Button? = null
    private var etFullName: EditText? = null
    private var etUsername: EditText? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var etPassword2: EditText? = null
    private var etPhone: EditText? = null
    private var fullName: String? = null
    private var username: String? = null
    private var email: String? = null
    private var password: String? = null
    private var password2: String? = null
    private var codeCountry: String? = null
    private var phoneNumber: String? = null
    private var tvLogin: TextView? = null
    private var tvNeedHelp: TextView? = null
    private var pickCode: CountryCodePicker? = null

    // firebase
    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null
    private val linkFirebase =
        "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sign_up)
        initView()
        setData()
    }

    private fun initView() {
        btnSignUp = findViewById(R.id.btn_create)
        tvLogin = findViewById(R.id.tv_login)
        tvNeedHelp = findViewById(R.id.tv_need_help)
        etFullName = findViewById(R.id.et_full_name)
        etUsername = findViewById(R.id.et_username)
        etEmail = findViewById(R.id.et_email)
        pickCode = findViewById(R.id.code_country)
        etPhone = findViewById(R.id.et_number)
        etPassword = findViewById(R.id.et_password)
        etPassword2 = findViewById(R.id.et_confirm_password)
    }

    private val data: Unit
         get() {
            fullName = etFullName!!.text.toString()
            username = etUsername!!.text.toString()
            email = etEmail!!.text.toString()
            password = etPassword!!.text.toString()
            password2 = etPassword2!!.text.toString()
            phoneNumber = etPhone!!.text.toString()
            codeCountry = pickCode!!.selectedCountryCodeWithPlus
        }

    private fun setData() {
        tvLogin!!.setOnClickListener { openLoginView() }
        btnSignUp!!.setOnClickListener {
            //get all input data
            data

            //check input
            if (!validateInput()) return@setOnClickListener
            else {
                //showOnDialog()

                // Send data to firebase
                database = FirebaseDatabase.getInstance(linkFirebase)
                myRef = database!!.getReference("users")
                val userHelperClass =
                    UserHelperClass(
                        fullName,
                        username,
                        password,
                        password2,
                        phoneNumber,
                        email
                    )
                myRef!!.child(username!!).setValue(userHelperClass)
                    .addOnSuccessListener {
                        Toast.makeText(this@SignUpActivity, "Sign Up Success", Toast.LENGTH_SHORT)
                            .show()
                        openLoginView()
                    }
                    .addOnFailureListener { e: Exception? ->
                        Toast.makeText(
                            this@SignUpActivity,
                            "Failed, detail: $e",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }

// check if username has been taken
//    private val isUsernameHasBeenTaken: Unit
//        get() {
//            val linkFirebase =
//                "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app"
//            val reference = FirebaseDatabase.getInstance().getReference("users")
//            reference.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    for (data in dataSnapshot.children) {
//                        if (data.child(username!!).exists()) {
//                            //do ur stuff
//                        } else {
//                            //do something if not exists
//                        }
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {}
//            })
//        }

    private fun openLoginView() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun validateFullName(): Boolean {
        return if (fullName!!.isEmpty()) {
            etFullName!!.setBackgroundResource(R.drawable.input_field_error)
            etFullName!!.error = "This field cannot be blank"
            false
        } else {
            etFullName!!.setBackgroundResource(R.drawable.input_field)
            true
        }
    }

    private fun validateUsername(): Boolean {
        return when {
          username!!.isEmpty() -> {
              etUsername!!.setBackgroundResource(R.drawable.input_field_error)
              etUsername!!.error = "This field cannot be blank"
              false
          }
          username!!.length >= 15 -> {
              etUsername!!.setBackgroundResource(R.drawable.input_field_error)
              etUsername!!.error = "Max length 15 character"
              false
          }
          username!!.contains(" ") -> {
              etUsername!!.setBackgroundResource(R.drawable.input_field_error)
              etUsername!!.error = "White spaces not allowed"
              false
          }
          else -> {
              etUsername!!.setBackgroundResource(R.drawable.input_field)
              true
          }
        }
    }

    private fun validateEmail(): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        return when {
          email!!.isEmpty() -> {
              etEmail!!.setBackgroundResource(R.drawable.input_field_error)
              etEmail!!.error = "This field cannot be blank"
              false
              //    } else if(!email.matches(getString(R.string.emailPattern))) {
          }
          email!!.matches(emailPattern.toRegex()) -> {
              etEmail!!.setBackgroundResource(R.drawable.input_field_error)
              etEmail!!.error = "Invalid email address"
              false
          }
          email!!.contains(" ") -> {
              etEmail!!.setBackgroundResource(R.drawable.input_field_error)
              etEmail!!.error = "White spaces not allowed"
              false
          }
          else -> {
              etEmail!!.setBackgroundResource(R.drawable.input_field)
              true
          }
        }
    }

    private fun validatePassword(): Boolean {
        val passwordPattern = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$")
        return if (password!!.isEmpty()) {
            etPassword!!.setBackgroundResource(R.drawable.input_field_error)
            etPassword!!.error = "This field cannot be blank"
            false
        } else if (!password!!.matches(passwordPattern.toRegex())) {
            etPassword!!.setBackgroundResource(R.drawable.input_field_error)
            etPassword!!.error = "Password is too weak"
            false
        } else {
            etPassword!!.setBackgroundResource(R.drawable.input_field)
            true
        }
    }

    private fun validatePassword2(): Boolean {
        //cek password
        Log.i("password", password!!)
        Log.i("password2", password2!!)
        return when {
          password2!!.isEmpty() -> {
              etPassword2!!.setBackgroundResource(R.drawable.input_field_error)
              etPassword2!!.error = "This field cannot be blank"
              false
          }
          password2 != password -> {
              etPassword2!!.setBackgroundResource(R.drawable.input_field_error)
              etPassword2!!.error = "Password not same"
              false
          }
          else -> {
              etPassword2!!.setBackgroundResource(R.drawable.input_field)
              true
          }
        }
    }

    private fun validatePhoneNumber(): Boolean {
        return if (phoneNumber!!.isEmpty()) {
            etPhone!!.setBackgroundResource(R.drawable.input_field_error)
            etPhone!!.error = "This field cannot be blank"
            false
        } else {
            etPhone!!.setBackgroundResource(R.drawable.input_field)

            //concat phone number and code country
            phoneNumber = codeCountry + phoneNumber
            true
        }
    }

    private fun validateInput(): Boolean {
        return !(!validateFullName()
                or !validateUsername()
                or !validateEmail()
                or !validatePhoneNumber()
                or !validatePassword()
                or !validatePassword2())
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