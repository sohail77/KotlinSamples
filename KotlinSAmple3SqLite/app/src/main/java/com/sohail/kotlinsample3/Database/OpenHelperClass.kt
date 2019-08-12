package com.sohail.kotlinsample3.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.sohail.kotlinsample3.Model.User

class OpenHelperClass (context: Context, cursorFactory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context,dbName,cursorFactory,dbVersion) {


    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable= ("CREATE TABLE " + tableName + " (" + columnId + " INTEGER PRIMARY KEY," + firstColumn + " TEXT," + secondColumn + " TEXT" + ")")
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS " + tableName)
        onCreate(p0)
    }


    fun addRow(user: User){
        val values=ContentValues()
        values.put(firstColumn,user.name)
        values.put(secondColumn,user.age)
        val db=this.writableDatabase
        db.insert(tableName,null,values)
        db.close()
    }

    fun showRows(): Cursor?{
        val db=this.readableDatabase
        return db.rawQuery("Select * from $tableName", null)
    }
    companion object {
        val dbName = "MyFirstDb"
        val dbVersion = 2
        val tableName = "Users"
        val columnId = "_ID"
        val firstColumn = "Name"
        val secondColumn = "Age"
    }
}