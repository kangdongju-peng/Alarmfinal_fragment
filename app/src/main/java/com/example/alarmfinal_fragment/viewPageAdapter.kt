package com.example.alarmfinal_fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class viewPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){
    private val items = ArrayList<Fragment>()
    init{
        items.add(Page1Fragment.newInstance(1))
        items.add(Page2Fragment.newInstance(3))

    }
    override fun getItem(position:Int) : Fragment {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size

    }


}