package com.example.socialfav

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.socialfav.fragments.feed.FeedFragment
import com.example.socialfav.fragments.nearbyusers.NearbyFragment
import com.example.socialfav.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseUser


class FavActivity : AppCompatActivity() {

    val currUser = ParseUser.getCurrentUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
//        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setDisplayUseLogoEnabled(true)

        val fragmentManager: FragmentManager = supportFragmentManager

        val navigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navigationView.setOnItemSelectedListener {
                item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()
                }
                R.id.action_nearby -> {
                    fragmentToShow = NearbyFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
            }

            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            // return true to say that we've handled this user interaction on the item
            true
        }

        // Set default selection
        navigationView.selectedItemId = R.id.action_home

        //profile photo in the bottom nav bar
        val menu = navigationView.menu

        val menuItem = menu.findItem(R.id.action_profile)

//        menuItem.setIcon(resources.getDrawable(R.drawable.ic_favorites_outline))

        Glide.with(this).asDrawable().load(currUser.getParseFile("profilePicture")?.url).circleCrop().into(object : CustomTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable?>?
            ) {
                menuItem.setIcon(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }

    companion object {
        const val TAG = "FavActivity"
    }
}