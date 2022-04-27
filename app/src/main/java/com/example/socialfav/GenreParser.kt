package com.example.socialfav

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import org.json.JSONArray

@ParseClassName("Genre")
class GenreParser: ParseObject() {


    //GenreName
    fun getGenre(): String?{
        return getString(KEY_TYPE)
    }
    fun setGenre(type: String){
        put(KEY_TYPE, type)
    }



    //GenreId
    fun getGenreId(): Int?{
        return getInt(KEY_NUMRECS)
    }
    fun setGenreId(GenreId : Int){
        put(KEY_NUMRECS, GenreId)
    }



    companion object{
        const val KEY_TYPE = "genre"
        const val KEY_NUMRECS = "genreid"
    }

}