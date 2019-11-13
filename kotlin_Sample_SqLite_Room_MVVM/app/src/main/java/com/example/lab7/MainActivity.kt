package com.example.lab7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab7.homefragment.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, fragment)
                .commit()
        }

    }
}
