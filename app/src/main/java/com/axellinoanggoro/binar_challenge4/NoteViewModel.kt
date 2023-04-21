package com.axellinoanggoro.binar_challenge4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(app: Application) : AndroidViewModel(app) {
    private var allNote: MutableLiveData<List<DataNote>> = MutableLiveData()

    init {
        getAllNote()
    }

    fun getAllNoteObservers(): MutableLiveData<List<DataNote>> {
        return allNote
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllNote() {
        GlobalScope.launch {
            val userDao = NoteDatabase.getInstance(getApplication())!!.noteDao()
            val listNote = userDao.getDataNote()
            allNote.postValue(listNote)
        }
    }


}