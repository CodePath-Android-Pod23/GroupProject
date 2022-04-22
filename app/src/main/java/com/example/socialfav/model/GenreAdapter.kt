//package com.example.socialfav.model
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.socialfav.R
//
//class GenreAdapter:  RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.genre_post, parent, false)
//
//        return ViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }
//
//    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        var tvTitle: TextView
//
//        init {
//            tvTitle = itemView.findViewById(R.id.tv_genreName)
//        }
//    }
//}