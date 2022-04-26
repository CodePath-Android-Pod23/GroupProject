package com.example.socialfav

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.socialfav.model.Genre
import com.example.socialfav.model.Genre.Companion.fromJson
import com.example.socialfav.model.Movie
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import okhttp3.Headers
import org.json.JSONException
import com.parse.ParseUser
import org.json.JSONArray

private const val TAG = "FAVActivity"
private const val MOVIE_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val GENRE_KEY = "https://api.themoviedb.org/3/genre/movie/list?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class FavActivity : AppCompatActivity() {
    private val movies = ArrayList<Movie>()
    private val genres = HashMap<Int, String>()
    private lateinit var rvMovies: RecyclerView
//    var genre_id = listOf<Int>(80, 27, 35)


    var selected = ParseUser.getCurrentUser().getJSONArray("genres")
    //TODO: Below is what you can used to get the array of genre object ids based on the users selection
    //var selected = ParseUser.getCurrentUser().getJSONArray("GenreArr")
    //TODO
    //You can then use these ids to query the Genre class on the parse server
    //Once you have the Genre parse object from the query, you can use the getGenreId func (See func in new Genre Class)
    //Hope this helps!


    var genre_string =""
    fun combine(selected: JSONArray?): String {
        if (selected != null) {
            for (i in 0 until selected.length()){
                genre_string += ("&with_genres=" + selected.getInt(i).toString())
            }
        }
        return genre_string
    }


    private val genre_url = "https://api.themoviedb.org/3/discover/movie?api_key="+ MOVIE_KEY + combine(selected)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
//        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
//        // Store the field now if you'd like without any need for casting
//        rvMovies= binding.rvMovies
        // Or use the binding to update views directly on the binding

//        // TODO: Delete after Test, navigate to nearby user page for testing
//        findViewById<Button>(R.id.btn_nearby).setOnClickListener {
//            val intent = Intent(this, NearbyUserActivity::class.java)
//            startActivity(intent)
//        }

        val client = AsyncHttpClient()
        //Log.i(TAG, selected.toString())
        Log.i("genre_url",genre_url )

        client.get(GENRE_KEY, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
//                Log.i(TAG, "onSuccess: JSON date $json")
                try {
                    val genreJsonArray = json.jsonObject.getJSONArray("genres")
                    val genreRaw = ArrayList<Genre>()
                    genreRaw.addAll(Genre.fromJsonArray(genreJsonArray))
                    for (genre in genreRaw){
                        genres.put(genre.genreId,genre.genreName)
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }

        })

        rvMovies = findViewById(R.id.rvFav)
        val movieAdapter= MovieAdapter(this, movies,genres)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        client.get(genre_url, object: JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "onSuccess: JSON date $json")
                try {
                    val movieJsonArray = json.jsonObject.getJSONArray("results")
                    movies.addAll(Movie.fromJsonArray(movieJsonArray))
                    movieAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Movie lists $movies")
                }catch (e: JSONException){
                    Log.e(TAG, "Encountered exception $e")
                }
            }
        })
    }

}