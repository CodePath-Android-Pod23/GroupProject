package com.example.socialfav

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialfav.fragments.nearbyusers.NearbyAdapter
import com.example.socialfav.fragments.nearbyusers.NearbyFragment
import com.parse.*
import org.json.JSONArray

class UserProfileActivity : AppCompatActivity() {

    lateinit var friendsRecyclerView: RecyclerView
    lateinit var adapter: UserAdapter
    var friendList = ArrayList<ParseUser>()

    var currUserFriendList :MutableList<Any> = arrayListOf()
    val currentUser: ParseUser = ParseUser.getCurrentUser()
    lateinit var user: ParseUser
    lateinit var friendID : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        user = intent.getParcelableExtra<ParseUser>("Friend")!!
        Log.i("user", user!!.objectId)

        val userPhoto = findViewById<ImageView>(R.id.avatar)

        Glide.with(this).load(user.getParseFile("profilePicture")?.url).circleCrop().into(userPhoto)
        findViewById<TextView>(R.id.tv_username).setText(user.getString("FullName"))
        findViewById<TextView>(R.id.tv_location).setText(user.getString("Location"))
        findViewById<TextView>(R.id.tv_caption).setText(user.getString("username"))

//        friendID = user.objectId
//        var friendArr: JSONArray? = currentUser.getJSONArray("Friends")
//        if (friendArr != null) {
//            currUserFriendList= friendArr.toMutableList()
//            if(currUserFriendList.contains(friendID)){
//                findViewById<Button>(R.id.btn_add).setText("Friends")
//            }else{
//                findViewById<Button>(R.id.btn_add).setText("Add Friend")
//            }
//        }

//        findViewById<Button>(R.id.btn_add).setOnClickListener {
//            //friendID = user.objectId
//            updateFriendList()
//        }
//
//        findViewById<ImageView>(R.id.iv_backbtn).setOnClickListener{
//           //TODO: Would need to add code to go start the NearbyUsers Fragment
//        }


        friendsRecyclerView = findViewById(R.id.rv_friends)
        adapter = UserAdapter(this, friendList)
        friendsRecyclerView.adapter = adapter
        friendsRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        getFriends(user)

    }

//    fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)
//
//    private fun updateFriendList(){
//        var friendArr: JSONArray? = currentUser.getJSONArray("Friends")
//
//        if (friendArr != null) {
//            Log.i(NearbyFragment.TAG, "Friend list was not null")
//            currUserFriendList = friendArr.toMutableList()
//            if(!currUserFriendList.contains(friendID)){
//                Log.i(NearbyAdapter.TAG, "Add Friend $friendID")
//                currUserFriendList.add(friendID)
//                findViewById<Button>(R.id.btn_add).setText("Friends")
//            }else if(currUserFriendList.contains(friendID)){
//                Log.i(NearbyAdapter.TAG, "Remove Friend $friendID")
//                currUserFriendList.remove(friendID)
//                findViewById<Button>(R.id.btn_add).setText("Add Friend")
//
//
//            }
//        }else{
//            Log.i(NearbyAdapter.TAG, "Friend list was empty. Adding friend")
//            currUserFriendList.add(friendID)
//            findViewById<Button>(R.id.btn_add).setText("Friends")
//
//        }
//
//        val arr = JSONArray(currUserFriendList)
//        currentUser.put("Friends", arr)
//
//        currentUser.saveInBackground() { e ->
//            if (e == null) {
//                Toast.makeText(this, "Friends updated!", Toast.LENGTH_SHORT).show()
//                Log.i(NearbyAdapter.TAG, "Friend list updated on Parse")
//            } else {
//                //Toast.makeText(context, "Update was unsuccessful.", Toast.LENGTH_SHORT).show()
//                e.printStackTrace()
//            }
//        }
//
//    }


    private fun getFriends(currUser: ParseUser) {

        val friends = currUser.getJSONArray("Friends")

        val queries = ArrayList<ParseQuery<ParseUser>>()

        if (friends != null ){
            for (i in 0 until friends.length()){
                val friendID = friends.getString(i)

                val query: ParseQuery<ParseUser> = ParseUser.getQuery()

                query.whereEqualTo("objectId", friendID)

                queries.add(query)
            }
        }

        val mainQuery: ParseQuery<ParseUser> = ParseQuery.or(queries)

        mainQuery.findInBackground(object: FindCallback<ParseUser> {
            override fun done(users: MutableList<ParseUser>?, e: ParseException?) {
                if (e != null) {
                    // Something has went wrong
                    Log.e(NearbyFragment.TAG, "Error fetching users")
                } else {
                    if (users != null) {
                        for (user in users) {
                            user.getString("FullName")?.let { Log.i("test", it) }
                        }

                        friendList.addAll(users)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })


    }
}