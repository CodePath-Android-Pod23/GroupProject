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


class NearbyAdapter(private val context: Context, private val nearbyUsers: List<ParseUser>)
    : RecyclerView.Adapter<NearbyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NearbyAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.users,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user= nearbyUsers.get(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return nearbyUsers.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvCity = itemView.findViewById<TextView>(R.id.tv_City)
        private val tvTag1 = itemView.findViewById<TextView>(R.id.tag1)
        private val tvTag2 = itemView.findViewById<TextView>(R.id.tag2)
        private val tvTag3 = itemView.findViewById<TextView>(R.id.tag3)
        private val tvTag4 = itemView.findViewById<TextView>(R.id.tag4)
        private val tvTag5 = itemView.findViewById<TextView>(R.id.tag5)
        fun bind(user: ParseUser){
            tvName.text = user.getString("FullName")
            tvCity.text = user.getString("Location")
//            Glide.with(context).load(movie.posterImageUrl).into(tvPoster)

            tvTag1.text = user.getJSONArray("Genres")?.getString(0)
            tvTag2.text = user.getJSONArray("Genres")?.getString(1)
            tvTag3.text = user.getJSONArray("Genres")?.getString(2)
            tvTag4.text = user.getJSONArray("Genres")?.getString(3)
            tvTag5.text = user.getJSONArray("Genres")?.getString(4)
        }
    }
}
