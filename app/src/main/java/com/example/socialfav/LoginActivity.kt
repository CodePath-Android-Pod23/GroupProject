package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btn_signIn).setOnClickListener{
            val username = findViewById<TextInputLayout>(R.id.et_username).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.et_password).editText?.text.toString()
            loginUser(username,password)
        }

        findViewById<ImageView>(R.id.iv_backbtn).setOnClickListener{
            val intent = Intent( this, MainActivity::class.java )
            startActivity(intent)
            finish()
        }

    }

    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Toast.makeText(this, "Log in was successful!", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Successful Log in")
                // go to Favs feed/activity
                goToFeed()
            } else {
                Toast.makeText(this, "Log in was unsuccessful. Please try again.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
                //Log.i(TAG, username)
            }})
        )
    }

    private fun goToFeed(){
        //TODO: change this to Recommendations Feed once this activity is created
        val intent = Intent(this,FavActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        val TAG = "LoginActivity"
    }
}