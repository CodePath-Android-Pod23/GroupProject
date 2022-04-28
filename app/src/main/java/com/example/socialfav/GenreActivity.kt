package com.example.socialfav

import RecyclerGenreAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.socialfav.fragments.FeedFragment
import com.parse.*
import org.json.JSONArray


class GenreActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerGenreAdapter.ViewHolder>? = null
    private var genresChosen : MutableList<String> = arrayListOf()
    private var genresPointers : MutableList<String> = arrayListOf()
    private var genreParserArr: MutableList<GenreParser> = arrayListOf()
    private val user = ParseUser.getCurrentUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        //Layout Manager is set to Grid Manager instead of Linear Layout Manager to have 2 columns
        layoutManager = GridLayoutManager(this, 2)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = layoutManager

        adapter = RecyclerGenreAdapter { genre -> onItemClick(genre) }

        recyclerView.adapter = adapter

        queryGenres()

        findViewById<Button>(R.id.btn_saveChoices).setOnClickListener {
            if (genresChosen.size >= 5) {
                makePointerArr()
                val userGenres = JSONArray(genresChosen)
                val pointerArr = JSONArray(genresPointers)
                user.put("Genres", userGenres)
                user.put("GenreArr", pointerArr)
                user.saveInBackground() { e ->
                    if (e == null) {
                        Toast.makeText(this, "Choices Saved!", Toast.LENGTH_SHORT).show()
                        Log.i(TAG, "Choices saved to profile")
                        val intent = Intent(this, FavActivity::class.java)
                        startActivity(intent)
                    } else {
                        //Toast.makeText(this, "Update was unsuccessful.", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                }
            } else {
                Toast.makeText(this, "Choose at least 5!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun onItemClick(genre: String) {
        if (genresChosen.contains(genre)){
            //TODO: convert to string to int based the map in the parse server
            genresChosen.remove(genre)
            //Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show()
        } else if (!genresChosen.contains(genre)){
            genresChosen.add(genre)
            //Toast.makeText(this, genre, Toast.LENGTH_SHORT).show()
        }
    }

    private fun makePointerArr(){
        if(genreParserArr != null){
            for(gen in genreParserArr){
                if(genresChosen.contains(gen.getGenre())){
                    genresPointers.add(gen.objectId)
                }
            }
        }

    }


 private fun queryGenres(){
     val query: ParseQuery<GenreParser> = ParseQuery.getQuery(GenreParser::class.java)
        query.findInBackground(object : FindCallback<GenreParser>{
            override fun done(genres: MutableList<GenreParser>?, e: ParseException?) {
                if(e != null){
                    Log.e(TAG, "Something went wrong with this query")
                }else{
                    if(genres !=null){
                        genreParserArr.addAll(genres)
                    }
                }
            }
        })
 }

    companion object{
        const val TAG = "GenreActivity"
    }
}



