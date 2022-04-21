package com.example.socialfav

import RecyclerGenreAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GenreActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerGenreAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        //Layout Manager is set to Grid Manager instead of Linear Layout Manager to have 2 columns
        layoutManager = GridLayoutManager(this, 2)

        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = layoutManager

        adapter = RecyclerGenreAdapter()

        recyclerView.adapter = adapter


        findViewById<Button>(R.id.btn_saveChoices).setOnClickListener{
            val intent = Intent( this, FavActivity::class.java )
            startActivity(intent)
        }
    }
}