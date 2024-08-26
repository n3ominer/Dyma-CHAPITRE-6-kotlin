package com.example.dymachap6

import android.accessibilityservice.GestureDescription.StrokeDescription
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), FragmentTransactionHandler {

    lateinit var textView1: TextView

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
