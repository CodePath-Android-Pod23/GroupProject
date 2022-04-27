package com.example.socialfav

import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.socialfav.model.Movie
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import okhttp3.Headers

private const val TAG = "DetailActivity"
const val YOUTUBE_API_KEY = "AIzaSyByGfu2SScgqi1GdSkB-gAzp8vbmR9EJVg"
private const val VIDEO_API_KEY = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class DetailActivity : YouTubeBaseActivity() {
    private lateinit var tvTitle: TextView
    private lateinit var tvOverview: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var ytPlayer: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        tvTitle = findViewById(R.id.tvTitle)
        tvOverview = findViewById(R.id.tvOverview)
        ratingBar = findViewById(R.id.rbVoteAverager)
        ytPlayer =findViewById(R.id.player)



        val movie = intent.getParcelableExtra<Movie>(Movie_Extra) as Movie
        Log.i(TAG, "Movie is $movie")
        tvTitle.text = movie.title
        tvOverview.text = movie.overview
        ratingBar.rating = movie.voted.toFloat()
        val client= AsyncHttpClient()
        client.get(VIDEO_API_KEY.format(movie.movieId), object:JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.i(TAG, "Video failed")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(TAG, "Video success")
                val results =  json.jsonObject.getJSONArray("results")
                if (results.length() == 0){
                    Log.w(TAG, "No movie trilers found")
                    return
                }
                val movieTrailerJson = results.getJSONObject(0)
                val youtubeKey = movieTrailerJson.getString("key")
                // play youtube with this trailer
                initializeYoutube(youtubeKey)
            }

        })


    }

    private fun initializeYoutube(youtubeKey: String) {
        ytPlayer.initialize(YOUTUBE_API_KEY, object: YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                Log.i(TAG,"OnInitializedListener successful")
                player?.cueVideo(youtubeKey);
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.i(TAG, "OnInitializedListener failure")
            }

        }   )
    }
}

