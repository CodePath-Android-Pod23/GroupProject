package com.example.socialfav

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.socialfav.fragments.FeedFragment
import com.example.socialfav.fragments.NearbyFragment
import com.example.socialfav.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)
        supportActionBar?.setDisplayShowHomeEnabled(true)
//        supportActionBar?.setLogo(R.drawable.nav_logo_whiteout)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
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
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }

    companion object {
        const val TAG = "FavActivity"
    }
}