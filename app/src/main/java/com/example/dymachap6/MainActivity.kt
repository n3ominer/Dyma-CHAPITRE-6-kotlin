package com.example.dymachap6

import android.accessibilityservice.GestureDescription.StrokeDescription
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), FragmentTransactionHandler {

    lateinit var textView1: TextView

    lateinit var toggle: ActionBarDrawerToggle

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
        this.displayAlertDialog()
        this.displayToast()

        this.setUpDrawerMenu()
    }

    private fun setUpDrawerMenu() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)

        this.toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer_text, R.string.close_drawer_string)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.nav_bar_activity -> {
                    Intent(this, BottomNavActivity::class.java).also {
                        drawerLayout.closeDrawer(GravityCompat.START)
                        startActivity(it)
                    }
                    Toast.makeText(this, "Bottom navigation activity", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_tab_layout_activity -> {
                    Intent(this, TabLayoutActivity::class.java).also {
                        drawerLayout.closeDrawer(GravityCompat.START)
                        startActivity(it)
                    }
                    Toast.makeText(this, "Tablayout activity", Toast.LENGTH_SHORT).show()
                }
                R.id.accueil -> Toast.makeText(this, "Accueil", Toast.LENGTH_LONG).show()
                R.id.profile_item -> Toast.makeText(this, "Profil", Toast.LENGTH_LONG).show()
                R.id.messages_item -> Toast.makeText(this, "Messages", Toast.LENGTH_LONG).show()
                R.id.rate_app_item -> Toast.makeText(this, "Note", Toast.LENGTH_LONG).show()
                R.id.share_app_item -> Toast.makeText(this, "Partager", Toast.LENGTH_LONG).show()
                R.id.activity_2_item -> {
                    Intent(this, SecondActivity::class.java).also {
                        drawerLayout.closeDrawer(GravityCompat.START)
                        startActivity(it)
                    }
                    Toast.makeText(this, "Activité 3", Toast.LENGTH_LONG).show()
                }
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) return true

        return super.onOptionsItemSelected(item)
    }

    private fun displayAlertDialog() {
        val customDialog = layoutInflater.inflate(R.layout.custom_dialog_alert, null)

        val customTitle = customDialog.findViewById<TextView>(R.id.alert_dialog_title_tv)
        customTitle.text = "Mon alerte custom"
        val customMessage = customDialog.findViewById<TextView>(R.id.alert_dialog_message_tv)
        customMessage.text = "Ceci est un message customisé d'alert pour démonstration"
        val customPositiveAction = customDialog.findViewById<TextView>(R.id.positive_action_btn)
        val customNegativeAction = customDialog.findViewById<TextView>(R.id.negative_action_btn)

        AlertDialog.Builder(this)
            .setView(customDialog)
            .create()
            .show()
    }

    private fun displayToast() {
        //Toast.makeText(this, "Text", Toast.LENGTH_LONG).show()

        val layout = layoutInflater.inflate(R.layout.custom_toast_layout, null)
        val toastCustom = Toast(this)
        toastCustom.duration = Toast.LENGTH_LONG
        toastCustom.view = layout
        toastCustom.show()
    }


    private fun addFragment1ToActivity() {
        val fragmentManager = supportFragmentManager

        val fragmanetTransaction = fragmentManager.beginTransaction()
        fragmanetTransaction.add(R.id.main_activity_fragment_container_1, MyFragment1.newInstance(this), "MY_FRAGMENT_1")
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
