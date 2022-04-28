//package com.example.socialfav
//
//import android.app.Activity
//import android.app.ActivityOptions
//import android.app.PendingIntent.getActivity
//import android.content.Context
//import android.content.Intent
//import android.content.res.Configuration
//import android.graphics.Color
//import android.util.Log
//import android.util.Pair
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.socialfav.model.Genre
//private const val TAG = "genreAdapter"
//class GenreAdapter(private val context: Context, private val genres: List<Genre> )
//
//    : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
//    val selected = ArrayList<Int>()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):GenreAdapter.ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.genre_post,parent,false)
//        val outview=  LayoutInflater.from(context).inflate(R.layout.activity_genre,parent,false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val genre= genres[position]
//        holder.tvText.text = genre.genreName
//        holder.rvGenre.setBackgroundColor(if (genre.isSelected) Color.GRAY else Color.WHITE)
//        holder.rvGenreId.setOnClickListener(View.OnClickListener {
//            genre.setSelected(!genre.isSelected)
//            holder.rvGenre.setBackgroundColor(if (genre.isSelected) Color.GRAY else Color.WHITE)
//            if(genre.isSelected){
//                selected.add(genre.genreId)
//            }
//
//        })
//
//    }
//
//    override fun getItemCount(): Int {
//        return genres.size
//    }
//
//    fun getSelectedItemCount(): Int {
//        Log.i(TAG,selected.size.toString() )
//        return selected.size
//    }
//
//    fun getSelectedItem(): ArrayList<Int> {
//        return selected
//    }
//
//
//    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val tvText = itemView.findViewById<TextView>(R.id.tv_text)
//        val rvGenre = itemView
//        val rvGenreId = itemView.findViewById<RelativeLayout>(R.id.rvGenreId)
//
//
//
//    }
//}