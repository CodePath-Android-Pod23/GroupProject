package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.parse.ParseUser

class UserDetails : AppCompatActivity() {
    val user = ParseUser.getCurrentUser()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        findViewById<TextInputLayout>(R.id.et_email).editText?.setText(user.email)
        findViewById<TextInputLayout>(R.id.et_fullName).editText?.setText(user.getString("FullName"))
        findViewById<TextInputLayout>(R.id.et_username).editText?.setText(user.username)

        findViewById<Button>(R.id.btn_saveProfile).setOnClickListener{
            val email = findViewById<TextInputLayout>(R.id.et_email).editText?.text.toString()
            val fullName = findViewById<TextInputLayout>(R.id.et_fullName).editText?.text.toString()
            val username = findViewById<TextInputLayout>(R.id.et_username).editText?.text.toString()
            val city = findViewById<TextInputLayout>(R.id.et_city).editText?.text.toString()
            //Log.i(TAG, city)
            val number = findViewById<TextInputLayout>(R.id.et_phoneNumber).editText?.text.toString()
            //Log.i(TAG, number)
            updateUserProfile(username,fullName, email, city,number)

        }


    }

    private fun updateUserProfile(username: String, fullName: String, email:String, city: String, number: String){
        user.put("username", username)
        user.put("email", email)
        user.put("FullName", fullName)
        user.put("Location", city)
        user.put("PhoneNumber", number)

        user.saveInBackground() { e ->
            if (e == null) {
                Toast.makeText(this, "Profile update was successful!", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Successful Profile update")
                goToProfileSettings()
            } else {
                Toast.makeText(this, "Profile update was unsuccessful. Please try again.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

    }

    private fun goToProfileSettings(){
        //TODO: Update the activity it leads to

        val intent = Intent( this, GenreActivity::class.java )
        //val intent = Intent(this,UserProfileScreen::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "UserDetails"
    }
}