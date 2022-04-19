package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class UserDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        findViewById<Button>(R.id.btn_saveProfile).setOnClickListener{
            val intent = Intent( this, GenreActivity::class.java )
            startActivity(intent)
        }
    }
}