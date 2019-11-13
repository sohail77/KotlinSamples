package com.example.lab7.addfragment

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.example.lab7.BR
import com.example.lab7.ObservableViewModel
import com.example.lab7.R
import com.example.lab7.database.Note
import com.example.lab7.database.NoteDatabaseDAO
import kotlinx.coroutines.*


class AddRecordViewModel(
    val database: NoteDatabaseDAO,
    application: Application
) : ObservableViewModel(application) {

    var shouldNavigateBack = MutableLiveData<Boolean>()
    private var modelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + modelJob)
    var categories = listOf<String>()
    var priorityList = listOf<String>()

    //two way binding observable field
    var titleField: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.titleField)
        }

    var descriptionField: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.descriptionField)
        }


    var categoryItemPosition: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.categoryItemPosition)
        }

    var priorityItemPosition: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.priorityItemPosition)
        }


    init {

        //get array from the resource file
        categories = application.resources.getStringArray(R.array.category_list).toList()
        priorityList = application.resources.getStringArray(R.array.importance_list).toList()

        shouldNavigateBack.value = false
    }


    override fun onCleared() {
        super.onCleared()
        modelJob.cancel()
    }


    fun writeDataInBackground() {
        val note =
            Note(
                title = titleField,
                description = descriptionField,
                category = categories.get(categoryItemPosition),
                importance = priorityList.get(priorityItemPosition)
            )
        uiScope.launch {
            insertIntoDataBase(note)
            shouldNavigateBack.value = true
        }

    }

    //write into the database on the IO thread
    private suspend fun insertIntoDataBase(note: Note) {
        withContext(Dispatchers.IO) {
            database.insert(note)
        }

    }
}