package com.example.dymachap6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class MyFragment2 : Fragment() {
    private lateinit var fragmentTransactionHandler: FragmentTransactionHandler
    private lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my2, container, false)

        this.btn1 = view.findViewById(R.id.frag2_btn)

        this.btn1.setOnClickListener {
            //fragmentTransactionHandler.fragmentBtnClickHandle(MyFragment1.newInstance(fragmentTransactionHandler))
            fragmentTransactionHandler.removeFragment(this)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(clickHandler: FragmentTransactionHandler) =
            MyFragment2().apply {
                this.fragmentTransactionHandler = clickHandler
            }
    }
}