package com.example.lab7.homefragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.lab7.adapter.NoteListAdapter
import com.example.lab7.database.Note
import com.example.lab7.database.NoteDatabaseDAO
import kotlinx.coroutines.*

class NoteListViewModel(
    val database: NoteDatabaseDAO,
    application: Application
) : AndroidViewModel(application) {

    private var modelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + modelJob)
    var noteList = database.getAllNotes()
    var adapter: NoteListAdapter


    init {
        getDataInBackground()
        //initialise the adapter
        adapter = NoteListAdapter(application)
    }

    fun getDataInBackground() {
        uiScope.launch {
            noteList = readData()
        }
    }

    //set the list in the adapter
    fun setUpAdapter(notes: List<Note>) {
        adapter.setNotes(notes)
        adapter.notifyDataSetChanged()
    }

    fun getNoteAdapter() = adapter

    //Read data from database on IO thread
    private suspend fun readData(): LiveData<List<Note>> {
        return withContext(Dispatchers.IO) {
            val list = database.getAllNotes()
            list
        }
    }

    //clear the  job
    override fun onCleared() {
        super.onCleared()
        modelJob.cancel()
    }

}
