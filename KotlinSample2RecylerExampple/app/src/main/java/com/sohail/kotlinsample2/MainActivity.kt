package com.sohail.kotlinsample2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sohail.kotlinsample2.Adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv.layoutManager=LinearLayoutManager(this)
        val list= mutableListOf("Car1", "Car2", "Car3", "Car4", "Car5", "Car6", "Car7", "Car8", "Car9")
        val adapter = CustomAdapter(this@MainActivity,list)
        rv.adapter=adapter

        //or directly use rv.adapter=CustomAdapter(this@MainActivity,list)
    }
}
