package com.axellinoanggoro.binar_challenge4.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DataNote(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var judul: String,
    var catatan: String
) : java.io.Serializable
