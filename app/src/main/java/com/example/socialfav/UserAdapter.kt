package com.example.socialfav

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseUser

class UserAdapter(private val context: Context, private val friends: List<ParseUser>): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_avatar, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = friends[position]
        holder.bind(friend)
    }

    override fun getItemCount(): Int {
        return friends.size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val ivAvatar = itemView.findViewById<ImageView>(R.id.iv_avatar)

        fun bind(user: ParseUser){
            Glide.with(context).load(user.getParseFile("profilePicture")?.url).circleCrop().into(ivAvatar)
        }
    }

}