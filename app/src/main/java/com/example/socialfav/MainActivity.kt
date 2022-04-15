package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_signIn).setOnClickListener{
            val intent = Intent( this, LoginActivity::class.java )
            startActivity(intent)
            finish()
        }

        findViewById<Button>(R.id.btn_signUp).setOnClickListener{
            val intent = Intent( this, SignUpActivity::class.java )
            startActivity(intent)
            finish()
        }
    }
}