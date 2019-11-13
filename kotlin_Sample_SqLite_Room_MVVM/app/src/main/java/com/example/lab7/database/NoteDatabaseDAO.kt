package com.example.lab7.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDatabaseDAO {

    @Insert
    fun insert(note: Note)

    @Query("SELECT * from note_data_table")
    fun getAllNotes(): LiveData<List<Note>>
}