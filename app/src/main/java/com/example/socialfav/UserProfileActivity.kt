package com.example.socialfav

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseObject
import com.parse.ParseQuery

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val bundle = intent.extras

        var s:String? = null

        s = bundle!!.getString("user")

        Log.i("user", s.toString())
    }
}