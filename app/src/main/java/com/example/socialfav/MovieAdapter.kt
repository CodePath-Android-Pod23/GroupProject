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
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialfav.model.Movie
val Movie_Extra = "Movie"

class MovieAdapter(private val context: Context, private val movies: List<Movie>, private val genres: HashMap<Int, String>, private val selected_genres: HashMap<Int?, String?>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie= movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener{
        private val tvPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvoverview = itemView.findViewById<TextView>(R.id.tvDescription)
        private val tvTag1 = itemView.findViewById<TextView>(R.id.tvTag1)
        private val tvTag2 = itemView.findViewById<TextView>(R.id.tvTag2)
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(movie: Movie){
            tvTitle.text = movie.title
            tvoverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(tvPoster)
            for (i in 0 until movie.genres.size) {
                if(movie.genres[i] in selected_genres) {
                    tvTag1.text = genres.getValue(movie.genres[i])
                    break
                }
            }

            if (movie.genres.size >1) {
                for (i in 0 until movie.genres.size) {
                    if (genres.getValue(movie.genres[i])?.equals(tvTag1.text) == false) {
                        tvTag2.text = genres.getValue(movie.genres[i])
                        break
                    }
                }
            }else{
                tvTag2.isVisible =false
            }

        }



        override fun onClick(p0: View?) {
            // 1. get notified with specific movie which was clicked
            val movie = movies[adapterPosition]
            Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            // 2. Use the intent to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)


            intent.putExtra(Movie_Extra, movie)


// Pass data object in the bundle and populate details activity.
            val pair1 = Pair.create<View,String>(tvPoster,"profile")
            val options = ActivityOptions.makeSceneTransitionAnimation(context as Activity?, pair1)

            context.startActivity(intent, options.toBundle())

        }
    }
}
