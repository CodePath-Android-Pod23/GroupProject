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
        queryUsers()
    }

    private fun queryUsers() {
//        adapter.clear()
        // 1. get the user's location and favorite genres
        val currentUser: ParseUser = ParseUser.getCurrentUser()
        val location = currentUser.getString("Location")
        val genres = currentUser.getJSONArray("Genres")
        Log.i(TAG, "Current user location is " + location)
        Log.i(TAG, "genres size is " + genres?.length())
        // 2. query to get users with the same location and at least one of the favorite genres
        // Specify which class to query
        val queries = ArrayList<ParseQuery<ParseUser>>()
        if (genres != null) {
            for (i in 0 until genres.length()) {
                val genre = genres.getString(i)
//                Log.i(TAG, "Genre is " + genre)
                val query: ParseQuery<ParseUser> = ParseUser.getQuery()
                // Return users with the same location as the current user
                query.whereEqualTo("Location", location)
                query.whereEqualTo("Genres", genre)
                queries.add(query)
            }
        }
        // Find users match at least one of the genres
        val mainQuery: ParseQuery<ParseUser> = ParseQuery.or(queries)
        mainQuery.findInBackground(object: FindCallback<ParseUser> {
//            query.findInBackground(object: FindCallback<ParseUser> {
            override fun done(users: MutableList<ParseUser>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(TAG, "Error fetching users")
                } else {
                    if (users != null) {
                        for (user in users) {
                            Log.i(TAG, "User: " + user.getString("FullName") + " , Location: " + user.getString("Location"))
                        }
//                        allPosts.addAll(posts)
//                        adapter.notifyDataSetChanged()
//                        swipeContainer.setRefreshing(false)
                    }
                }
            }
        })
    }

    companion object{
        const val TAG = "NearbyUserActivity"
    }

}