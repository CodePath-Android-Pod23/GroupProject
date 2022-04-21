package com.example.socialfav

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO: uncomment this to have user persistence
//        if(ParseUser.getCurrentUser()!=null){
//            val intent = Intent(this,FavActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        findViewById<Button>(R.id.btn_signIn).setOnClickListener{
            val intent = Intent( this, LoginActivity::class.java )
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_signUp).setOnClickListener{
            val intent = Intent( this, SignUpActivity::class.java )
            startActivity(intent)
        }

        //queryItems()
    }

//    fun queryItems(){
//        val query: ParseQuery<Item> = ParseQuery.getQuery(Item::class.java)
//        query.findInBackground(object : FindCallback<Item>{
//            override fun done(items: MutableList<Item>?, e: ParseException?) {
//                if(e != null){
//                    Log.e(TAG, "Something went wrong with this query")
//                }else{
//                    if(items !=null){
//                        for(item in items){
//                            Log.i(TAG, "Movie: " + item.getTitle())
//                            Log.i(TAG, "Genres: " + item.getGenre())
//                        }
//                    }
//                }
//            }
//
//        })
//    }

    companion object{
        const val TAG = "MainActivity"
    }
}