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
    val title:String,
    val overview: String,
    private val backdropPath: String,
    val voted: Double,
    val genres: ArrayList<Int>
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

                        genreToList(movieJson.getJSONArray("genre_ids"))


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
//    companion object {
//        @Throws(JSONException::class)
//        fun fromJson(movieJson: JSONObject): Movie {
//            val movie = Movie()
//            movie.movieId =  movieJson.getInt("id")
//            movie.posterImageUrl = movieJson.getString("poster_path")
//            movie.title =  movieJson.getString("title")
//            movie.overview =  movieJson.getString("overview")
//            movie.backdropImageUrl = movieJson.getString("backdrop_path")
//            movie.voted = movieJson.getDouble("vote_average")
//            for (i in 0 until movieJson.getJSONArray("genres_id").length()){
//                movie.genres?.add(movieJson.getJSONArray("genres_id").getInt(i))
//            }
//            return movie
//        }
//
//
//        fun fromJsonArray(movieJsonArray: JSONArray): Collection<Movie> {
//            val movies = ArrayList<Movie>()
//            for (i in 0 until movieJsonArray.length()) {
//                movies.add(fromJson(movieJsonArray.getJSONObject((i))))
//            }
//            return movies
//        }
//
//
//    }

