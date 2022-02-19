package com.example.mycake

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    private var tvSignUp: TextView? = null
    private var buttonLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_welcome)
        initView()
        gotoSignUp()
        gotoLogin()
    }

    fun initView() {
        tvSignUp = findViewById(R.id.tv_sign_up)
        buttonLogin = findViewById(R.id.btn_login)
    }

    fun gotoSignUp() {
        tvSignUp!!.setOnClickListener {
            val intent = Intent(this@WelcomeActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    fun gotoLogin() {
        buttonLogin!!.setOnClickListener { v: View? ->
            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}