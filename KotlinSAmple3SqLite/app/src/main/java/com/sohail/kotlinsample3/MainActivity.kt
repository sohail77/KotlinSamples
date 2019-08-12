package com.sohail.kotlinsample3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sohail.kotlinsample3.Database.OpenHelperClass
import com.sohail.kotlinsample3.Model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveBtn.setOnClickListener {
            val dbHandler=OpenHelperClass(this,null)
            val user= User(nameField.text.toString(),ageField.text.toString())
            dbHandler.addRow(user)
            Toast.makeText(this,"Row Added",Toast.LENGTH_LONG).show()
        }

        showBtn.setOnClickListener {
            dataTxt.setText("")
            val dbHandler=OpenHelperClass(this,null)
            val cursor= dbHandler.showRows()
            cursor!!.moveToFirst()
            dataTxt.append("Name: " + cursor.getString(cursor.getColumnIndex(OpenHelperClass.firstColumn)))
            dataTxt.append(" Age:" + cursor.getString(cursor.getColumnIndex(OpenHelperClass.secondColumn)))
            dataTxt.append("\n")
            while(cursor.moveToNext()){
                dataTxt.append("Name: " + cursor.getString(cursor.getColumnIndex(OpenHelperClass.firstColumn)))
                dataTxt.append(" Age:" + cursor.getString(cursor.getColumnIndex(OpenHelperClass.secondColumn)))
                dataTxt.append("\n")
            }
            cursor.close()
        }
    }
}
