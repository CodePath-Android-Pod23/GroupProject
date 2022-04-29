package com.example.socialfav.model

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONException

import org.json.JSONObject
@Parcelize
data class Movie(
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String,
    private val backdropPath: String,
    val voted: Double,
    val genres: ArrayList<Int>,

    // newly added
    val release: String,
    val voteCount: Int
    ) : Parcelable {
    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w342/$posterPath"
    val backdropImageUrl = "https://image.tmdb.org/t/p/w342/$backdropPath"


    companion object {
        fun fromJsonArray(movieJSONArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJSONArray.length()) {
                val movieJson = movieJSONArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getString("backdrop_path"),
                        movieJson.getDouble("vote_average"),
                        genreToList(movieJson.getJSONArray("genre_ids")),
                        // newly added
                        movieJson.getString("release_date"),
                        movieJson.getInt("vote_count")
                    )
                )
            }
            return movies
        }

        fun genreToList(genreJSONArray: JSONArray): ArrayList<Int> {
            var genres = ArrayList<Int>()
            for (i in 0 until genreJSONArray.length()) {
                genres.add(genreJSONArray.getInt(i))

            }
            return genres
        }
    }
}

