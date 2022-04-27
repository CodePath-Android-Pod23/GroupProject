package com.example.socialfav

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialfav.model.Movie


class NearbyAdapter(private val context: Context, private val users: List<User>, private val genres: HashMap<Int, String>)
    : RecyclerView.Adapter<NearbyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NearbyAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie= users[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvoverview = itemView.findViewById<TextView>(R.id.tvDescription)
        private val tvTag1 = itemView.findViewById<TextView>(R.id.tvTag1)
        private val tvTag2 = itemView.findViewById<TextView>(R.id.tvTag2)
        fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvoverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(tvPoster)

            tvTag1.text = genres.getValue(movie.genres[0])

            if (movie.genres.size >1){
                tvTag2.text = genres.getValue(movie.genres[1])
            }

        }
    }
}
