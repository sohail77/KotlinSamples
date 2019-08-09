package com.sohail.kotlinsample1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        colorChangeTxt.setOnClickListener {
            layout.setBackgroundColor(Color.GREEN)
            colorChangeTxt.setText("Voila")
        }
    }
}
