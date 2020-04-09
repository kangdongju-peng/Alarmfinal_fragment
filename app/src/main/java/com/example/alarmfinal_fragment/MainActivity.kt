package com.example.alarmfinal_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val Adapter = viewPageAdapter(supportFragmentManager)
        view_pager.adapter = Adapter
        view_pager.currentItem=1

    }
}
