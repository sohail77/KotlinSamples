package com.example.lab7.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_data_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String,

    //Adding the ColumnInfo annotation is not necessary if you want the column name to be the same as the property name
    var description: String,


    var category: String,


    var importance: String
)