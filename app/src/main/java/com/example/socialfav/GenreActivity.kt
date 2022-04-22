package com.example.socialfav

import RecyclerGenreAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseUser
import org.json.JSONArray

class GenreActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerGenreAdapter.ViewHolder>? = null
    private var genresChosen : MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        //Layout Manager is set to Grid Manager instead of Linear Layout Manager to have 2 columns
        layoutManager = GridLayoutManager(this, 2)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = layoutManager

        adapter = RecyclerGenreAdapter { genre -> onItemClick(genre) }

        recyclerView.adapter = adapter


        findViewById<Button>(R.id.btn_saveChoices).setOnClickListener {
            if (genresChosen.size >= 5) {
                val userGenres = JSONArray(genresChosen)
                val user = ParseUser.getCurrentUser()
                user.put("Genres", userGenres)
                user.saveInBackground() { e ->
                    if (e == null) {
                        Toast.makeText(this, "Choice Saved!", Toast.LENGTH_SHORT).show()
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
            genresChosen.remove(genre)
            //Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show()
        } else if (!genresChosen.contains(genre)){
            genresChosen.add(genre)
            //Toast.makeText(this, genre, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        const val TAG = "GenreActivity"
    }
}


