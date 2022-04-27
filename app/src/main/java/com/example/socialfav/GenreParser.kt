package com.example.socialfav

import com.parse.ParseClassName
import com.parse.ParseObject

@ParseClassName("Genre")
class GenreParser: ParseObject() {

    //genre
    fun getGenre(): String?{
        return getString(KEY_GENRE)
    }
//    fun setGenre(type: String){
//        put(KEY_GENRE, type)
//    }
    //genreID
    fun getGenreID(): Int?{
        return getInt(KEY_GENRE_ID)
    }
//    fun setGenreID(id: Int){
//        put(KEY_GENRE_ID, id)
//    }



    companion object {
        const val KEY_GENRE = "genre"
        const val KEY_GENRE_ID = "genreid"
    }
}