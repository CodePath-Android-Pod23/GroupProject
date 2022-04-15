package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        findViewById<Button>(R.id.btn_signUp).setOnClickListener{
            val intent = Intent( this, userDetails::class.java )
            startActivity(intent)
        }
    }
}