package com.example.mycake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.mycake.R
import android.widget.TextView
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import android.content.Intent
import com.google.firebase.database.DatabaseError
import android.widget.Toast
import android.content.DialogInterface
import com.hbb20.CountryCodePicker
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.OnFailureListener
import android.annotation.SuppressLint
import android.view.animation.Animation
import android.os.Looper
import android.widget.RelativeLayout

class UserHelperClass {
    var fullName: String? = null
    var username: String? = null
    var password: String? = null
    var password2: String? = null
    var phoneNumber: String? = null
    var email: String? = null

    constructor(
        fullName: String?,
        username: String?,
        password: String?,
        password2: String?,
        phoneNumber: String?,
        email: String?
    ) {
        this.fullName = fullName
        this.username = username
        this.password = password
        this.password2 = password2
        this.phoneNumber = phoneNumber
        this.email = email
    }

    constructor() {}
}