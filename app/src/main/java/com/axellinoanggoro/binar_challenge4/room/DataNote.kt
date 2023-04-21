package com.axellinoanggoro.binar_challenge4.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DataNote(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var judul: String,
    var catatan: String
) : Parcelable
