package com.example.socialfav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class NearbyUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_user)
    }

    fun queryUsers() {
//        adapter.clear()
        // 1. get the user's location and favorite genres
        val currentUser: ParseUser = ParseUser.getCurrentUser()
        val location = currentUser.getString(User.KEY_LOCATION)
        val genres = currentUser.getJSONArray(User.KEY_GENRES)
        // 2. query to get users with the same location and at least one of the favorite genres
        // Specify which class to query
        val queries = ArrayList<ParseQuery<User>>()
        if (genres != null) {
            for (i in 0 until genres.length()) {
                val genre = genres.getJSONObject(i).toString()
                val query: ParseQuery<User> = ParseQuery.getQuery(User::class.java)
                // Return users with the same location as the current user
                query.whereEqualTo(User.KEY_LOCATION, location)
                query.whereEqualTo(User.KEY_GENRES, genre)
                queries.add(query)
            }
        }
        // Find users match at least one of the genres
        val mainQuery: ParseQuery<User> = ParseQuery.or(queries)
        mainQuery.findInBackground(object: FindCallback<User> {
            override fun done(users: MutableList<User>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(MainActivity.TAG, "Error fetching users")
                } else {
                    if (users != null) {
                        for (user in users) {
                            Log.i(MainActivity.TAG, "User: " + user.getFullName() + " , Location: " + user.getLocation())
                        }
//                        allPosts.addAll(posts)
//                        adapter.notifyDataSetChanged()
//                        swipeContainer.setRefreshing(false)
                    }
                }
            }
        })
    }

}