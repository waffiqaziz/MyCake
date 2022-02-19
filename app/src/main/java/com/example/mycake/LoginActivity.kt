package com.example.mycake

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
  private var btnLogin: Button? = null
  private var tvRegister: TextView? = null
  private var etUsername: EditText? = null
  private var etPassword: EditText? = null
  private var usernameInput: String? = null
  private var passwordInput: String? = null
  private var alertDialogBuilder: AlertDialog.Builder? = null
  private var backButton: ImageView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    @Suppress("DEPRECATION")
    window.setFlags(
      WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN
    )

    setContentView(R.layout.activity_login)
    alertDialogBuilder = AlertDialog.Builder(this)
    initView()
    setData()
  }

  private fun initView() {
    btnLogin = findViewById(R.id.btn_login)
    tvRegister = findViewById(R.id.tv_sign_up)
    etUsername = findViewById(R.id.et_username)
    etPassword = findViewById(R.id.et_password)
    backButton = findViewById(R.id.iv_back)
  }

  private fun validateUsername(): Boolean {
    return if (usernameInput!!.isEmpty()) {
      etUsername!!.setBackgroundResource(R.drawable.input_field_error)
      etUsername!!.error = "This field cannot be blank"
      false
    } else {
      etUsername!!.setBackgroundResource(R.drawable.input_field)
      true
    }
  }

  private fun validatePassword(): Boolean {
    return if (passwordInput!!.isEmpty()) {
      etPassword!!.setBackgroundResource(R.drawable.input_field_error)
      etPassword!!.error = "This field cannot be blank"
      false
    } else {
      etPassword!!.setBackgroundResource(R.drawable.input_field)
      true
    }
  }

  private fun setData() {
    btnLogin!!.setOnClickListener {

      // get data
      usernameInput = etUsername!!.text.toString()
      passwordInput = etPassword!!.text.toString()

      //action
//      sendDataViaIntent();
      loginUser()
    }
    tvRegister!!.setOnClickListener { openRegisterView() }
    backButton!!.setOnClickListener { openWelcomeView() }
  }

  private fun loginUser() {
    if (!validateUsername() or !validatePassword()) {
      return
    } else {
      isUser
      Log.d("cek", "is user")
    }
  }//            etUsername.setError(null);

  // log
//          etUsername.setError(null);

  //check if user match with password
  //check user is exist/no
  private val isUser: Unit
    get() {
      val linkFirebase =
        "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app"
      val reference = FirebaseDatabase.getInstance(linkFirebase).getReference("users")
      Log.i("cek", "cek username")
      val query = reference.orderByChild("username").equalTo(usernameInput)
      query.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {

          // check user is exist/no
          if (snapshot.exists()) {

            // etUsername.setError(null);
            val passwordDB = snapshot.child(usernameInput!!).child("password").getValue(
              String::class.java
            )

            //check if user match with password
            when (passwordDB) {
              null -> {
                Log.i("cek pass", "passwordDB null")
                etPassword!!.error = "Wrong password"
                return
              }
              passwordInput -> {

                // etUsername.setError(null);
                val emailFromDB =
                  snapshot.child(usernameInput!!).child("email").getValue(
                    String::class.java
                  )
                val fullNameFromDB =
                  snapshot.child(usernameInput!!).child("fullName").getValue(
                    String::class.java
                  )
                val passwordFromDB =
                  snapshot.child(usernameInput!!).child("password").getValue(
                    String::class.java
                  )
                val phoneNumberFromDB =
                  snapshot.child(usernameInput!!).child("phoneNumber").getValue(
                    String::class.java
                  )
                val usernameFromDB =
                  snapshot.child(usernameInput!!).child("username").getValue(
                    String::class.java
                  )
                val intent = Intent(
                  applicationContext,
                  UserProfileActivity::class.java
                )

                intent.putExtra("fullName", fullNameFromDB)
                intent.putExtra("username", usernameFromDB)
                intent.putExtra("email", emailFromDB)
                intent.putExtra("password", passwordFromDB)
                intent.putExtra("phoneNumber", phoneNumberFromDB)

                // log
                Log.i("fullName", fullNameFromDB!!)
                Log.i("username", usernameFromDB!!)
                Log.i("email", emailFromDB!!)
                Log.i("pass", passwordFromDB!!)
                Log.i("phone", phoneNumberFromDB!!)
                startActivity(intent)
              }
              else -> {
                etPassword!!.error = "Wrong password"
              }
            }
          } else {
            etUsername!!.error = "No such User exists"
            etUsername!!.requestFocus()
          }
        }

        override fun onCancelled(error: DatabaseError) {
          Toast.makeText(this@LoginActivity, "Firebase error", Toast.LENGTH_SHORT).show()
        }
      })
    }

  private fun openRegisterView() {
    val intent = Intent(this, SignUpActivity::class.java)
    startActivity(intent)
  }

  private fun openWelcomeView() {
    val intent = Intent(this, WelcomeActivity::class.java)
    startActivity(intent)
  }

  //  if back button pressed, do
  override fun onBackPressed() {
//    super.onBackPressed();
    Toast.makeText(this@LoginActivity, "Anda menekan tombol back", Toast.LENGTH_SHORT).show()
    alertDialogBuilder!!.setTitle("Tutup Aplikasi")
    alertDialogBuilder!!
      .setMessage("Apakah anda ingin menutup aplikasi?")
      .setCancelable(false)
      .setPositiveButton(
        "Ya"
      ) { dialog: DialogInterface?, id: Int ->
        Toast.makeText(
          this@LoginActivity,
          "Anda menekan Ya",
          Toast.LENGTH_SHORT
        ).show()
      }
      .setNegativeButton("Tidak") { dialog: DialogInterface, id: Int ->
        dialog.cancel()
        Toast.makeText(this@LoginActivity, "Anda menekan Tidak", Toast.LENGTH_SHORT).show()
      }
      .create()
      .show()
  } //  private void sendDataViaIntent(){
  //    Intent intent = new Intent(LoginActivity.this, ShowLoginActivity.class);
  //    intent.putExtra("username",username);
  //    intent.putExtra("password",password);
  //    startActivity(intent);
  //  }
}