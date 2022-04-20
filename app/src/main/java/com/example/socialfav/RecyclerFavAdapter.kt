package com.example.socialfav

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerFavAdapter: RecyclerView.Adapter<RecyclerFavAdapter.ViewHolder>() {

    //sample array of movie title for testing item recyclerView
    private var movies = arrayOf("Movie 1", "Movie 2", "Movie 3", "Movie 4")

    //sample array of movie descriptions testing item recyclerView
    private var details = arrayOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et.", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et.")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerFavAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerFavAdapter.ViewHolder, position: Int) {
        holder.tvTitle.text = movies[position]
        holder.tvDescription.text = details[position]
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvDescription: TextView

        init {
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvDescription = itemView.findViewById(R.id.tv_description)
        }
    }
}