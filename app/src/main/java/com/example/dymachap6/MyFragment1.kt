package com.example.dymachap6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFragment1 : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_my1, container, false)

        this.btn1 = view.findViewById(R.id.frag1_btn)

        this.btn1.setOnClickListener {
            fragmentTransactionHandler.fragmentBtnClickHandle(MyFragment2.newInstance(fragmentTransactionHandler))

            (activity as MainActivity).getDataFromFragment1("Hello from fragment !")
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("http://nav.frag.3.dyma/path")
            }
            startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(clickHandler: FragmentTransactionHandler) =
            MyFragment1().apply {
                this.fragmentTransactionHandler = clickHandler
            }
    }
}