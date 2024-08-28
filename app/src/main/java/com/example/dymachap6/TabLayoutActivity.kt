package com.example.dymachap6

import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tab_layout)

        this.tabLayout = findViewById(R.id.tab_layout)
        this.viewPager = findViewById(R.id.view_pager)

        val tabLayoutMediator = TabLayoutMediator(this.tabLayout, this.viewPager) { tab, position ->
            when(position) {
                0 -> tab.text = "Fragment 1"
                1 -> tab.text = "Fragment 2"
                2 -> tab.text = "Fragment 3"
            }
        }

        this.viewPager.adapter = MyTabLayoutAdapter(this)
        tabLayoutMediator.attach()
    }

    class MyTabLayoutAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> MyFragment1()
                1 -> MyFragment2()
                2 -> MyFragment3()
                else -> MyFragment1()
            }
        }

    }
}