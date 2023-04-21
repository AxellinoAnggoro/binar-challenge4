package com.axellinoanggoro.binar_challenge4.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    @Insert
    fun insertData(dataNote: DataNote)

    @Query("SELECT * FROM DataNote ORDER BY id DESC")
    fun getDataNote(): List<DataNote>

    @Query("SELECT * FROM DataNote ORDER BY id ASC")
    fun getDataNoteAsc(): List<DataNote>

    @Delete
    fun deleteNote(dataNote: DataNote)

    @Update
    fun updateNote(dataNote: DataNote)
}