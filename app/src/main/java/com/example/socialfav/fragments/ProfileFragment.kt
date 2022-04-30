package com.example.socialfav.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialfav.R
import com.example.socialfav.UserAdapter
import com.example.socialfav.fragments.nearbyusers.NearbyFragment
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : Fragment() {

    lateinit var friendsRecyclerView: RecyclerView
    lateinit var adapter: UserAdapter
    var friendList = ArrayList<ParseUser>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = view.findViewById<ImageButton>(R.id.button)

        btn.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(requireActivity().supportFragmentManager, ModalBottomSheet.TAG)
        }

        val userPhoto = view.findViewById<ImageView>(R.id.avatar)
        val currUser = ParseUser.getCurrentUser()
        val photo = currUser.getParseFile("profilePicture")
        if (photo != null) {
            Glide.with(this.requireContext()).load(photo.url).circleCrop().into(userPhoto)
        }
        view.findViewById<TextView>(R.id.tv_username).setText(currUser.getString("FullName"))
        view.findViewById<TextView>(R.id.tv_location).setText(currUser.getString("Location"))
        view.findViewById<TextView>(R.id.tv_caption).setText(currUser.getString("username"))

        friendsRecyclerView = view.findViewById(R.id.rv_friends)
        adapter = UserAdapter(requireContext(), friendList)
        friendsRecyclerView.adapter = adapter
        friendsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        getFriends(currUser)
    }

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
        if (queries.size == 0) return
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