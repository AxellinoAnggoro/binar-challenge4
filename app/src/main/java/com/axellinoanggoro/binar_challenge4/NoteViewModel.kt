package com.axellinoanggoro.binar_challenge4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private val list = arrayListOf(
        DataNote(
            "tes", "tes"
        ),
        DataNote(
            "tes", "tes"
        )
    )

    val noteList : MutableLiveData<List<DataNote>> = MutableLiveData(list)

    fun getNoteList(){
        val note = list
        noteList.value = note
    }
}