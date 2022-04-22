package com.example.socialfav

import com.parse.ParseClassName
import com.parse.ParseObject
import org.json.JSONArray

@ParseClassName("User")
class User: ParseObject() {

    //Full Name
    fun getFullName(): String?{
        return getString(KEY_FULLNAME)
    }
    fun setFullName(name: String){
        put(KEY_FULLNAME, name)
    }

    //Location
    fun getLocation(): String?{
        return getString(KEY_LOCATION)
    }
    fun setLocation(location: String){
        put(KEY_LOCATION, location)
    }

    //Friends
    fun getFriends(): JSONArray?{
        return getJSONArray(KEY_FRIENDS)
    }
    fun setGenre(friends : JSONArray){
        put(KEY_FRIENDS, friends)
    }

    //Recommendations
    fun getRecommendations(): JSONArray?{
        return getJSONArray(KEY_RECOMMENDATIONS)
    }
    fun setRecommendations(recommendations: JSONArray){
        put(KEY_RECOMMENDATIONS, recommendations)
    }

    //Genres
    fun getGenres(): JSONArray?{
        return getJSONArray(KEY_GENRES)
    }
    fun setGenres(genres : JSONArray){
        put(KEY_GENRES, genres)
    }

    //Username
    fun getUsername(): String?{
        return getString(KEY_USERNAME)
    }
    fun setUsername(username : String){
        put(KEY_USERNAME, username)
    }

    //Email
    fun getEmail(): String?{
        return getString(KEY_EMAIL)
    }
    fun setEmail(email : String){
        put(KEY_EMAIL, email)
    }

    //Phone Number
    fun getPhoneNumber(): String?{
        return getString(KEY_PHONE)
    }
    fun setPhoneNumber(phone: String){
        put(KEY_PHONE, phone)
    }

    //Password
    fun getPassword(): String?{
        return getString(KEY_PASSWORD)
    }
    fun setPassword(password : String){
        put(KEY_PASSWORD, password)
    }


    companion object{
        const val KEY_FULLNAME = "FullName"
        const val KEY_LOCATION = "Location"
        const val KEY_FRIENDS = "Friends"
        const val KEY_RECOMMENDATIONS = "Recommendations"
        const val KEY_GENRES = "Genres"
        const val KEY_USERNAME = "UserName"
        const val KEY_EMAIL = "Email"
        const val KEY_PHONE = "PhoneNumber"
        const val KEY_PASSWORD = "Password"
    }

}