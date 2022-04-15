package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        findViewById<ImageView>(R.id.imageView).setOnClickListener{
            val intent = Intent( this, userDetails::class.java )
            startActivity(intent)
        }
    }
}