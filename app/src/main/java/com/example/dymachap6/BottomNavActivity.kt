package com.example.dymachap6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavActivity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView

    lateinit var homeFragment: MyFragment1
    lateinit var profileFragment: MyFragment2
    lateinit var activity2Fragment: MyFragment3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_nav)

        homeFragment = MyFragment1()
        profileFragment = MyFragment2()
        activity2Fragment = MyFragment3()

        this.initBottomNavigationView(initFragment = homeFragment)
    }

    private fun initBottomNavigationView(initFragment: Fragment) {
        bottomNav = findViewById(R.id.navigation_bar)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_fragment_container_view, initFragment)
        transaction.commit()

    }
}