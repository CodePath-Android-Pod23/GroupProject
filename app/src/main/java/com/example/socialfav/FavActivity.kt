package com.example.socialfav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.socialfav.model.Genre
import com.example.socialfav.model.Movie
import com.parse.FindCallback
import com.parse.ParseException
import okhttp3.Headers
import org.json.JSONException
import com.parse.ParseUser
import org.json.JSONArray
import com.parse.ParseQuery


private const val TAG = "FAVActivity"
private const val MOVIE_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val GENRE_KEY = "https://api.themoviedb.org/3/genre/movie/list?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class FavActivity : AppCompatActivity() {
    private val movies = ArrayList<Movie>()
    private val genres = HashMap<Int?, String?>()
    private lateinit var rvMovies: RecyclerView
    var selected_genreParser: MutableList<GenreParser> = mutableListOf()
    var selected_genres= HashMap<Int?, String?>()
//    var genre_id = listOf<Int>(80, 27, 35)
    var selected = ParseUser.getCurrentUser().getJSONArray("GenreArr")
    // TODO: test the movies with selected genres from parse server
    // TODO: when selected is not null, something is going wrong
    var genre_string =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        queryGenre()

    }
  fun getFav(genre_url: String){
        val client = AsyncHttpClient()
        rvMovies = findViewById(R.id.rvFav)
        val movieAdapter= MovieAdapter(this, movies,genres, selected_genres)
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

    fun combine(): String {
        for (i in 0 until selected_genreParser.size){

            val id = selected_genreParser.get(i).getGenreId()
            if(id != null) {
                genre_string += ("&with_genres=" + id)
            }
        }
        return genre_string
    }


    fun queryGenre() {
        // Specify which class to query

        val query: ParseQuery<GenreParser> = ParseQuery.getQuery("Genre")
        var genreId: Int? = null

        var selected = ParseUser.getCurrentUser().getJSONArray("GenreArr")
        var selected_str = mutableListOf<String>()
        if (selected != null) {
            for (i in 0 until selected.length()) {
                selected_str.add(selected.get(i).toString())
            }
        }
        Log.i(TAG, "$selected")
//        query.whereEqualTo("genreid", 28)
        query.whereContainedIn("objectId",selected_str)

        query.findInBackground(object : FindCallback<GenreParser> {
            override fun done(genreParsers: MutableList<GenreParser>?, e: ParseException?) {
                Log.i(TAG, "Query start")
                if (e != null) {
                    // something went wrong
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (genreParsers != null) {
                        for (genre in genreParsers) {
                            Log.i(
                                TAG,
                                "GenreId:" + genre.getGenreId() + ", Genre: " + genre.getGenre()
                            )
                            selected_genres.put(genre.getGenreId(),genre.getGenre())
                        }
                        selected_genreParser.addAll(genreParsers)
                        val genre_url = "https://api.themoviedb.org/3/discover/movie?api_key="+ MOVIE_KEY + combine() + "&sort_by=popularity.desc"
                        getGenreList()
                        getFav(genre_url)
                    } else {
                        Log.i(TAG, "No match")
                    }
                }
            }
        })
    }

    fun getGenreList() {
        val client = AsyncHttpClient()
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
                    for (genre in genreRaw) {
                        genres.put(genre.genreId, genre.genreName)
                    }

                } catch (e: JSONException) {
                    Log.e(TAG, "Encountered exception $e")
                }
            }

        })
    }


}



