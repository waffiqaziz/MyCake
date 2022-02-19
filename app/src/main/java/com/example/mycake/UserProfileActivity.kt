package com.example.mycake

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {
    private var tvFullName: TextView? = null
    private var ivLogOut: ImageView? = null
    private var ivBack: ImageView? = null
    private var fullName: String? = null
    private var username: String? = null
    private var password: String? = null
    private var email: String? = null
    private var phoneNumber: String? = null
    private var rlEditProfile: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        initView()
        setData()
    }

    private fun initView() {
        tvFullName = findViewById(R.id.fullName)
        ivLogOut = findViewById(R.id.logOut)
        rlEditProfile = findViewById(R.id.btn_edit_profile)
        ivBack = findViewById(R.id.iv_back)
    }

    private fun setData() {
        val intentGet = intent
        fullName = intentGet.getStringExtra("fullName")
        username = intentGet.getStringExtra("username")
        email = intentGet.getStringExtra("email")
        password = intentGet.getStringExtra("password")
        phoneNumber = intentGet.getStringExtra("phoneNumber")
        tvFullName!!.text = fullName

        rlEditProfile!!.setOnClickListener {
            val intent = Intent(applicationContext, UserEditActivity::class.java)
            intent.putExtra("fullName", fullName)
            intent.putExtra("username", username)
            intent.putExtra("email", email)
            intent.putExtra("password", password)
            intent.putExtra("phoneNumber", phoneNumber)
            startActivity(intent)
        }
        ivLogOut!!.setOnClickListener { openLoginView() }
        ivBack!!.setOnClickListener {
            Toast.makeText(this@UserProfileActivity, "Go to Home", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openLoginView() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}