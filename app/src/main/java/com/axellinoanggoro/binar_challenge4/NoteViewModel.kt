package com.axellinoanggoro.binar_challenge4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    lateinit var allNote: MutableLiveData<List<DataNote>>

    init {
        allNote = MutableLiveData()
        getAllNote()
    }

    fun getAllNoteObservers() : MutableLiveData<List<DataNote>>{
        return allNote
    }

    fun getAllNote(){
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listNote = userDao.getDataNote()
            allNote.postValue(listNote)
        }
    }


}