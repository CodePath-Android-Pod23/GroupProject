package com.example.socialfav.model

import android.content.ContentValues
import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
class Genre(var genreId: Int =0, var genreName: String="", var isSelected: Boolean = false) : Parcelable {
    companion object {
        fun fromJson(jsonObject: JSONObject): Genre {
            val genre = Genre()
            genre.genreId = jsonObject.getInt("id")
            genre.genreName = jsonObject.getString("name")

            Log.i(ContentValues.TAG, "$jsonObject")

            return genre
        }

        fun fromJsonArray(jsonArray: JSONArray): List<Genre> {
            val genres = ArrayList<Genre>()
            for (i in 0 until jsonArray.length()) {
                genres.add(fromJson(jsonArray.getJSONObject((i))))
            }
            return genres
        }

    }
    @JvmName("setSelected1")
    fun setSelected(selected: Boolean) {
        isSelected = selected
    }



}