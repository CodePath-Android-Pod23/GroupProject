package com.example.socialfav.fragments.nearbyusers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.socialfav.R
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
        // 1. get the current user's location and favorite genres
        val currentUser: ParseUser = ParseUser.getCurrentUser()
        val location = currentUser.getString("Location")
        val genres = currentUser.getJSONArray("Genres")
        val id = currentUser.objectId
        Log.i(TAG, "Current user location is " + location)
        Log.i(TAG, "genres size is " + genres?.length())
        // 2. query to get users with the same location and at least one of the favorite genres
        val queries = ArrayList<ParseQuery<ParseUser>>()
        if (genres != null) {
            for (i in 0 until genres.length()) {
                val genre = genres.getString(i)
                val query: ParseQuery<ParseUser> = ParseUser.getQuery()
                // Match location
                query.whereEqualTo("Location", location)
                // Match Genre
                query.whereEqualTo("Genres", genre)
                // Exclude current user
                query.whereNotEqualTo("objectId", id)
                queries.add(query)
            }
        }
        // Find users match at least one of the genres use compound queries
        val mainQuery: ParseQuery<ParseUser> = ParseQuery.or(queries)
        mainQuery.findInBackground(object: FindCallback<ParseUser> {
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