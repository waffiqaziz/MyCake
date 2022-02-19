package com.example.mycake

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserEditActivity : AppCompatActivity() {
  private var etFullName: EditText? = null
  private var etEmail: EditText? = null
  private var etPassword: EditText? = null
  private var etPhoneNumber: EditText? = null
  private var fullName: String? = null
  private var username: String? = null
  private var password: String? = null
  private var email: String? = null
  private var phoneNumber: String? = null
  private var btnUpdate: Button? = null
  private var ivBack: ImageView? = null
  private var reference: DatabaseReference? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_edit)
    initView()
    setData()
  }

  private fun setData() {

    // get intent
    val intent = intent
    username = intent.getStringExtra("username")
    fullName = intent.getStringExtra("fullName")
    email = intent.getStringExtra("email")
    password = intent.getStringExtra("password")
    phoneNumber = intent.getStringExtra("phoneNumber")

    //set edit text
    etFullName!!.setText(fullName)
    etEmail!!.setText(email)
    etPassword!!.setText(password)
    etPhoneNumber!!.setText(phoneNumber)

    btnUpdate!!.setOnClickListener {
      val linkFirebase =
        "https://mycake-c1bdf-default-rtdb.asia-southeast1.firebasedatabase.app"
      reference = FirebaseDatabase.getInstance(linkFirebase).getReference("users")
      updateDataUser()
    }

    ivBack!!.setOnClickListener { gotoUserProfile() }
  }

  private fun initView() {
    etFullName = findViewById(R.id.et_full_name)
    etEmail = findViewById(R.id.et_email)
    etPassword = findViewById(R.id.et_password_update)
    etPhoneNumber = findViewById(R.id.et_phone_number)
    btnUpdate = findViewById(R.id.btn_update)
    ivBack = findViewById(R.id.iv_back)
  }

  private fun updateDataUser() {
    if (isFullNameChanged or isPasswordChanged) {
      Toast.makeText(this@UserEditActivity, "Data has been update", Toast.LENGTH_SHORT).show()
      gotoUserProfile()
    } else {
      Toast.makeText(this@UserEditActivity, "Data is same cannot update", Toast.LENGTH_SHORT)
        .show()
    }
  }

  private val isFullNameChanged: Boolean
    get() = if (fullName != etFullName!!.text.toString()) {
      reference!!.child(username!!).child("fullName").setValue(etFullName!!.text.toString())
      true
    } else true

  private val isPasswordChanged: Boolean
    get() = if (password != etPassword!!.text.toString()) {
      reference!!.child(username!!).child("password").setValue(etPassword!!.text.toString())
      true
    } else false


  private fun gotoUserProfile() {
    val intent = Intent(this, UserProfileActivity::class.java)
    startActivity(intent)
  }
}