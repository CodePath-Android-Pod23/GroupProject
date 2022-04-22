package com.example.socialfav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.socialfav.model.Genre
import com.example.socialfav.model.Genre.Companion.fromJson
import com.example.socialfav.model.Movie
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

    // TODO: key should be "Genres"
    var selected = ParseUser.getCurrentUser().getJSONArray("genres")
    var test = ParseUser.getCurrentUser().getJSONArray("Genres")

    // TODO: test the movies with selected genres from parse server
    // TODO: when selected is not null, something is going wrong
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



        val client = AsyncHttpClient()
        Log.i(TAG, selected.toString())
        // TODO: test Log statement prints list of genres but selected Log statement prints null
        Log.i(TAG, test.toString())
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