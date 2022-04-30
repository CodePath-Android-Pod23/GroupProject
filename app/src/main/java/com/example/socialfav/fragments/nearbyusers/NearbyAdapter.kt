package com.example.socialfav.fragments.nearbyusers

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialfav.*
import com.parse.ParseUser
import org.json.JSONArray


class NearbyAdapter(private val context: Context, private val nearbyUsers: ArrayList<ParseUser>)
    : RecyclerView.Adapter<NearbyAdapter.ViewHolder>() {
    var FriendList :MutableList<Any> = arrayListOf()
    val currentUser: ParseUser = ParseUser.getCurrentUser()
    lateinit var friendID: String
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user= nearbyUsers.get(position)
        holder.bind(user)

        holder.itemView.findViewById<Button>(R.id.outlinedButton).setOnClickListener(View.OnClickListener {
            friendID = user.objectId
            updateFriendList(holder)
        })
    }

    override fun getItemCount(): Int {
        return nearbyUsers.size
    }

    // Clean all elements of the recycler
    fun clear() {
        nearbyUsers.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View ): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvCity = itemView.findViewById<TextView>(R.id.tv_City)
        private val ivProfile = itemView.findViewById<ImageView>(R.id.iv_avatar)
        private val tvTag1 = itemView.findViewById<TextView>(R.id.tag1)
        private val tvTag2 = itemView.findViewById<TextView>(R.id.tag2)
        private val tvTag3 = itemView.findViewById<TextView>(R.id.tag3)
        private val tvTag4 = itemView.findViewById<TextView>(R.id.tag4)
        private val tvTag5 = itemView.findViewById<TextView>(R.id.tag5)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(user: ParseUser){
            tvName.text = user.getString("FullName")
            tvCity.text = user.getString("Location")
            val photo = user.getParseFile("profilePicture")
            if (photo != null) {
                Glide.with(context).load(photo.url).override(50, 50).circleCrop().into(ivProfile)
            }

            friendID = user.objectId
            var friendArr: JSONArray? = currentUser.getJSONArray("Friends")
            if (friendArr != null) {
                FriendList = friendArr.toMutableList()
                if(FriendList.contains(friendID)){
                    itemView.findViewById<Button>(R.id.outlinedButton).setText("Friends")
                } else{
                    itemView.findViewById<Button>(R.id.outlinedButton).setText("Add Friend")
                }
            }
            tvTag1.text = user.getJSONArray("Genres")?.getString(0)
            tvTag2.text = user.getJSONArray("Genres")?.getString(1)
            tvTag3.text = user.getJSONArray("Genres")?.getString(2)
            tvTag4.text = user.getJSONArray("Genres")?.getString(3)
            tvTag5.text = user.getJSONArray("Genres")?.getString(4)
        }

        override fun onClick(p0: View?) {
            // 1. get notified with specific user which was clicked

            val user = nearbyUsers[adapterPosition]
            Toast.makeText(context, "User Selected", Toast.LENGTH_SHORT).show()

            val intent = Intent( context, UserProfileActivity::class.java )
            intent.putExtra("Friend", user)
            context.startActivity(intent)
        }


    }
    fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)

    private fun updateFriendList(v: ViewHolder){
        var friendArr: JSONArray? = currentUser.getJSONArray("Friends")

        if (friendArr != null) {
            Log.i(NearbyFragment.TAG, "Friend list was not null")
            FriendList = friendArr.toMutableList()
            if(!FriendList.contains(friendID)){
                Log.i(TAG, "Add Friend $friendID")
                FriendList.add(friendID)
                v.itemView.findViewById<Button>(R.id.outlinedButton).setText("Friends")
            }else if(FriendList.contains(friendID)){
                Log.i(TAG, "Remove Friend $friendID")
                FriendList.remove(friendID)
                v.itemView.findViewById<Button>(R.id.outlinedButton).setText("Add Friend")
            }
        }else{
            Log.i(TAG, "Friend list was empty. Adding friend")
            FriendList.add(friendID)
            v.itemView.findViewById<Button>(R.id.outlinedButton).setText("Friends")

        }

        val arr = JSONArray(FriendList)
        currentUser.put("Friends", arr)

        currentUser.saveInBackground() { e ->
            if (e == null) {
                Toast.makeText(context, "Friends updated!", Toast.LENGTH_SHORT).show()
                Log.i(TAG, "Friend list updated on Parse")
            } else {
                //Toast.makeText(context, "Update was unsuccessful.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

    }

    companion object{
        const val TAG = "NearbyAdapter"
    }

}
