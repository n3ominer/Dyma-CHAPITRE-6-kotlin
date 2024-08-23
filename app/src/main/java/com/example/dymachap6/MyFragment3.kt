package com.example.dymachap6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class MyFragment3 : Fragment() {
    lateinit var frag3TextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my3, container, false)
        this.frag3TextView = view.findViewById(R.id.fragment3_center_tv)
        var fragToDisplay = 3

       activity?.intent?.data?.let {
           fragToDisplay = (it.getQueryParameter("frag") ?: "3").toInt()
       }

       handleDeepLinkData(fragToDisplay)


        this.frag3TextView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("CENTER_TEXT", "FRAGMENT 3 TEXT")
            }
            findNavController().navigate(R.id.navigateFromMyFragment3ToMyFragment4, bundle)

        }

        return view
    }

    private fun handleDeepLinkData(fragTag: Int) {
        if (fragTag == 4) {
            val bundle = Bundle().apply {
                putString("CENTER_TEXT", "DISPLAYING FRAGMENT FROM DEEP LINK")
            }
            findNavController().navigate(R.id.navigateFromMyFragment3ToMyFragment4, bundle)
            activity?.intent?.data = null
        }
    }
}