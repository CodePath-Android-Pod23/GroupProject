package com.example.socialfav

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.socialfav.fragments.nearbyusers.NearbyAdapter
import com.example.socialfav.model.Movie
import com.parse.*
import org.json.JSONArray


val Movie_Extra = "Movie"
val currentUser = ParseUser.getCurrentUser()

class MovieAdapter(private val context: Context, private val movies: List<Movie>, private val genres: HashMap<Int, String>, private val selected_genres: HashMap<Int?, String?>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private var itemArr : MutableList<Item> = arrayListOf()
    private var currItem = Item()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post,parent,false)
        queryItems()
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.findViewById<ToggleButton>(R.id.tb_recommend).setOnClickListener {
            makeMovieItem(holder, movie)
        }
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

    fun JSONArray.toMutableList(): MutableList<Any> = MutableList(length(), this::get)

    private fun makeMovieItem(holder: ViewHolder, movie: Movie){

        var favArr: JSONArray? = currentUser.getJSONArray("Recommendations")
        var FavList :MutableList<Any> = arrayListOf()

        var movieRecByUserArr : JSONArray?= JSONArray()
        var movieID = findItemID(movie)
        for(item in itemArr){
            if(item.getTitle().equals(movie.title)){
                currItem = item
                movieRecByUserArr = item.getRecommendedBy()
            }
        }

         if(movieID.length == 0){
             movieID = currItem.objectId
         }
        //var movieRecByUserArr : JSONArray? = currItem.getRecommendedBy()
        var movieRecList :MutableList<Any> = arrayListOf()
        if(movieRecByUserArr !=null){
            movieRecList =movieRecByUserArr.toMutableList()
        }

        if (favArr != null) {
            Log.i(TAG, "Favs list was not null")
            FavList = favArr.toMutableList()

            if(!FavList.contains(movieID)){
                Log.i(TAG, "Add Movie to Favs $movieID")
                FavList.add(movieID)
                movieRecList.add(currentUser.objectId)

            }else if(FavList.contains(movieID)){
                Log.i(TAG, "Remove Movie from Favs$movieID")
                FavList.remove(movieID)
                movieRecList.remove(currentUser.objectId)
            }
        }else{
            Log.i(TAG, "Favs list was empty. Adding new fav movie")
            FavList.add(movieID)
            movieRecList.add(currentUser.objectId)
        }

        val newFavArr = JSONArray(FavList)
        currentUser.put("Recommendations", newFavArr)

        currentUser.saveInBackground() { e ->
            if (e == null) {
                Toast.makeText(context, "Favs updated!", Toast.LENGTH_SHORT).show()
                Log.i(NearbyAdapter.TAG, "Favs list updated on Parse")
            } else {
                //Toast.makeText(context, "Update was unsuccessful.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

        var RecBy = JSONArray(movieRecList)
        Log.i(TAG, RecBy.toString())
        currItem.put("RecommendedBy", RecBy)

        currItem.saveInBackground(){e ->
            if (e == null) {
                Toast.makeText(context, "Recommended by list updated!", Toast.LENGTH_SHORT).show()
                Log.i(NearbyAdapter.TAG, "Recommended by list updated on Parse")
            } else {
                //Toast.makeText(context, "Update was unsuccessful.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

    }


    private fun findItemID(movie:Movie): String{
        var newItem = Item()
        for(item in itemArr){
            if(item.getTitle().equals(movie.title)){
                //newItem = item;
                currItem = item
                return item.objectId
            }
        }

        newItem.setGenre(JSONArray(movie.genres))
        newItem.setTitle(movie.title)
        newItem.setImage(movie.posterImageUrl)
        newItem.setSynopsis(movie.overview)
        newItem.setType("movie")

        currItem = newItem

        newItem.save()
//        {e ->
//            if (e == null) {
//                Toast.makeText(context, "New Movie item created!", Toast.LENGTH_SHORT).show()
//                Log.i(NearbyAdapter.TAG, "New Movie item added on Parse")
//            } else {
//                //Toast.makeText(context, "Update was unsuccessful.", Toast.LENGTH_SHORT).show()
//                e.printStackTrace()
//            }
//        }

        var size = itemArr.size
        queryItems()
//        while(itemArr.size == size){
//            Log.i(TAG,"Waiting")
//        }

        var id = ""
        for(item in itemArr){
            if(item.getTitle().equals(movie.title)){
                //newItem = item;
                currItem = item
                id = item.objectId
            }
        }

        return id
    }


    private fun queryItems(){
        val query: ParseQuery<Item> = ParseQuery.getQuery(Item::class.java)
        query.findInBackground(object : FindCallback<Item>{
            override fun done(items: MutableList<Item>?, e: ParseException?) {
                if(e != null){
                    Log.e(TAG, "Something went wrong with this query")
                }else{
                    if(items !=null){
                        itemArr.addAll(items)
                    }
                }
            }

        })
    }

    companion object{
        const val TAG = "MovieAdapter"
    }
}
