package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.parse.ParseUser

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        findViewById<Button>(R.id.btn_signUp).setOnClickListener{
            val username = findViewById<TextInputLayout>(R.id.et_username).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.et_password).editText?.text.toString()
            val fullName = findViewById<TextInputLayout>(R.id.et_fullName).editText?.text.toString()
            val emailOrMobile = findViewById<TextInputLayout>(R.id.et_mobileNumber).editText?.text.toString()

            signUpUser(username,password, fullName, emailOrMobile)

//            val intent = Intent( this, userDetails::class.java )
//            startActivity(intent)
//            finish()
        }
    }


    private fun signUpUser(username: String, password: String,fullName: String, emailOrMobile:String){
        val user = ParseUser()

        user.setUsername(username)
        user.setPassword(password)
        //user.setFullName(fullName)
        //user.setEmail(emailOrMobile) OR user.setNumber(emailOrMobile)

        user.signUpInBackground { e ->
            if (e == null) {
                Toast.makeText(this, "Sign up was successful!", Toast.LENGTH_SHORT).show()
                Log.i(LoginActivity.TAG, "Successful Sigh Up")
                goToFeed()
            } else {
                Toast.makeText(this, "Sign up was unsuccessful. Please try again.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun goToFeed(){
        val intent = Intent(this,FavActivity::class.java)
        startActivity(intent)
        finish()
    }

}