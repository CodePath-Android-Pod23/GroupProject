package com.example.socialfav

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import org.json.JSONArray

@ParseClassName("Item")
class Item: ParseObject() {

    //Type
    fun getType(): String?{
        return getString(KEY_TYPE)
    }
    fun setType(type: String){
        put(KEY_TYPE, type)
    }

    //Title
    fun getTitle(): String?{
        return getString(KEY_TITLE)
    }
    fun setTitle(title: String){
        put(KEY_TITLE, title)
    }

    //Genre
    fun getGenre(): JSONArray?{
        return getJSONArray(KEY_GENRE)
    }
    fun setGenre(genres : JSONArray){
        put(KEY_GENRE, genres)
    }

    //Synopsis
    fun getSynopsis(): String?{
        return getString(KEY_SYNOPSIS)
    }
    fun setSynopsis(synopsis: String){
        put(KEY_SYNOPSIS, synopsis)
    }

    //Image
    fun getImage(): String?{
        return getString(KEY_IMAGE)
    }
    fun setImage(image : String){
        put(KEY_IMAGE, image)
    }

    //Num of Recs
    fun getNumOfRecommendations(): Int?{
        return getInt(KEY_NUMRECS)
    }
    fun setNumOfRecommendations(numOfRecs : Int){
        put(KEY_NUMRECS, numOfRecs)
    }

    //Rec By
    fun getRecommendedBy(): JSONArray?{
        return getJSONArray(KEY_RECBY)
    }
    fun setRecommendedBy(recBy : JSONArray){
        put(KEY_RECBY, recBy)
    }

    //Links
    fun getLinks(): JSONArray?{
        return getJSONArray(KEY_LINKS)
    }
    fun setLinks(links: JSONArray){
        put(KEY_LINKS, links)
    }


    companion object{
        const val KEY_TYPE = "Type"
        const val KEY_TITLE = "Title"
        const val KEY_GENRE = "Genre"
        const val KEY_SYNOPSIS = "Synopsis"
        const val KEY_IMAGE = "PosterUrl"
        const val KEY_NUMRECS = "NumOfRecommendations"
        const val KEY_RECBY = "RecommendedBy"
        const val KEY_LINKS = "Links"
    }

}