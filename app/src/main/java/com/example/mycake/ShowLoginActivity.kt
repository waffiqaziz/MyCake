package com.example.mycake

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowLoginActivity : AppCompatActivity() {
    private var tvUsername: TextView? = null
    private var tvPassword: TextView? = null
    private var username: String? = null
    private var password: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_input_login)
        initView()
        setData()
        todoRegister()
    }

    private fun initView() {
        tvUsername = findViewById(R.id.input_username)
        tvPassword = findViewById(R.id.input_password)
    }

    private fun setData() {
        val intent = intent
        username = intent.getStringExtra("username")
        password = intent.getStringExtra("password")
        tvUsername!!.text = username
        tvPassword!!.text = password
    }

    private fun todoRegister() {}
}