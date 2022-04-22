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

    // 1. get the user's location and favorite genres

    // 2. query to get users with the same location and at least one of the favorite genres


    fun queryUsers(location: String, genres: List<String>) {
//        adapter.clear()
        // Specify which class to query
        val query: ParseQuery<User> = ParseQuery.getQuery(User::class.java)
        // Only return posts from current signed in user
        query.whereEqualTo(User.KEY_LOCATION, location)
        // return posts in descending order
        query.addDescendingOrder("createdAt")

        // only return the most recent 20 posts

        query.findInBackground(object: FindCallback<User> {
            override fun done(posts: MutableList<User>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(MainActivity.TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(MainActivity.TAG, "Post: " + post.getDescription() + " , username: " + post.getUser()?.username)
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