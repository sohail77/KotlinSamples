package com.sohail.kotlinsample1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //No need for findViewById anymore Yay!!
        switchBtn.setOnClickListener {
            val i=Intent(this@MainActivity,SecondActivity::class.java)
            startActivity(i)
        }
    }
}
