package com.example.socialfav.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfav.NearbyAdapter
import com.example.socialfav.R
import com.parse.*
import org.json.JSONArray


class NearbyFragment : Fragment() {

    lateinit var usersRecyclerView: RecyclerView
    lateinit var adapter: NearbyAdapter
//    var FriendList :MutableList<Any> = arrayListOf()
//    val currentUser: ParseUser = ParseUser.getCurrentUser()
    var nearbyUsers = ArrayList<ParseUser>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nearby, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersRecyclerView = view.findViewById(R.id.rv_users)
        adapter = NearbyAdapter(requireContext(), nearbyUsers)
        usersRecyclerView.adapter = adapter
        usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
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
                        nearbyUsers.addAll(users)
                        adapter.notifyDataSetChanged()
//                        swipeContainer.setRefreshing(false)
                    }
                }
            }
        })
    }

    companion object{
        const val TAG = "NearbyFragment"
    }
}

