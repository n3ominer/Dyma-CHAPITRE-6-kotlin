package com.example.dymachap6

import android.accessibilityservice.GestureDescription.StrokeDescription
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity(), FragmentTransactionHandler {

    lateinit var textView1: TextView
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        this.textView1 = findViewById(R.id.tv1)
        this.textView1.setOnClickListener {
            Intent(this, SecondActivity::class.java).also {
                startActivity(it)
            }
        }
        this.addFragment1ToActivity()
        this.addFragment3ToActivity()
    }
    private fun addFragment1ToActivity() {
        val fragmentManager = supportFragmentManager

        val fragmanetTransaction = fragmentManager.beginTransaction()
        fragmanetTransaction.add(R.id.main_activity_fragment_container_1, MyFragment1.newInstance(this), "MY_FRAGMENT_1")
        fragmanetTransaction.commit()
    }

    private fun addFragment3ToActivity() {
        val fragmentManager = supportFragmentManager

        val fragmanetTransaction = fragmentManager.beginTransaction()
        fragmanetTransaction.add(R.id.main_activity_fragment_container_2, MyFragment3(), "MY_FRAGMENT_3")
        fragmanetTransaction.commit()
    }

    private fun replaceFragment(fragmentToReplace: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_activity_fragment_container_1, fragmentToReplace)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }


    fun getDataFromFragment1(data: String) {
        Toast.makeText(this, "Data from fragment 1: $data", Toast.LENGTH_LONG).show()
    }

    override fun fragmentBtnClickHandle(fragment: Fragment) {
        this.replaceFragment(fragment)
    }

    override fun removeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .remove(fragment)
            .commit()
    }
}


interface FragmentTransactionHandler {
    fun fragmentBtnClickHandle(fragment: Fragment)

    fun removeFragment(fragment: Fragment)
}
