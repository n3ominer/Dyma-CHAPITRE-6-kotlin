package com.example.dymachap6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class MyFragment4 : Fragment() {

    lateinit var fragment4TextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my4, container, false)

        this.fragment4TextView = view.findViewById(R.id.fragment4_center_tv)

        val textArg = arguments?.getString("CENTER_TEXT") ?: "DEFAULT TEXT"

        this.fragment4TextView.text = textArg

        this.fragment4TextView.setOnClickListener {
            findNavController().navigate(R.id.navigateFromMyFragment4ToMyFragment3)
        }
        return view
    }



}