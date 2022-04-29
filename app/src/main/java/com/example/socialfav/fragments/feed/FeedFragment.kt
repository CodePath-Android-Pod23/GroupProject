package com.example.socialfav.fragments.feed

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.socialfav.*
import com.example.socialfav.model.Genre
import com.example.socialfav.model.Movie
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import okhttp3.Headers
import org.json.JSONException
import androidx.fragment.app.Fragment

private const val TAG = "FAVActivity"
private const val MOVIE_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val GENRE_KEY = "https://api.themoviedb.org/3/genre/movie/list?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class FeedFragment : Fragment() {

    val user = ParseUser.getCurrentUser()

    private val movies = ArrayList<Movie>()
    val genres = HashMap<Int, String>()
    //    var genre_id = listOf<Int>(80, 27, 35)

    var selected_genreParser: MutableList<GenreParser> = mutableListOf()
    var selected_genres= HashMap<Int?, String?>()

    var selected = ParseUser.getCurrentUser().getJSONArray("genres")

    private lateinit var rvMovies: RecyclerView
    lateinit var adapter: MovieAdapter

    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queryGenre(view)
    }

    fun queryGenre(view: View) {
        // Specify which class to query

        val query: ParseQuery<GenreParser> = ParseQuery.getQuery("Genre")

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
                                "GenreId:" + genre.getGenreID() + ", Genre: " + genre.getGenre()
                            )
                            selected_genres.put(genre.getGenreID(),genre.getGenre())
                        }
                        selected_genreParser.addAll(genreParsers)
                        val genre_url = "https://api.themoviedb.org/3/discover/movie?api_key="+ MOVIE_KEY + combine() + "&sort_by=popularity.desc"
                        getGenreList()
                        getFav( view,genre_url)
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

    fun getFav(view: View, genre_url: String){
        val client = AsyncHttpClient()
        rvMovies = view.findViewById(R.id.rvFav)
        val movieAdapter = MovieAdapter(requireContext(), movies, genres, selected_genres)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(context)

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
        var genre_string = ""
        for (i in 0 until this.selected_genreParser.size){

            val id = this.selected_genreParser.get(i).getGenreID()
            if(id != null) {
                genre_string += ("&with_genres=" + id)
            }
        }
        return genre_string
    }
}