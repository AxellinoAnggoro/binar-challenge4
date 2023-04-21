package com.axellinoanggoro.binar_challenge4

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_challenge4.databinding.NoteItemBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterNote(var listNote: List<DataNote>) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    var dbNote: NoteDatabase? = null

    class ViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNote.ViewHolder {
        var view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterNote.ViewHolder, position: Int) {
        holder.binding.noteItem = listNote[position]

        holder.binding.btnDeleteNote.setOnClickListener {
            dbNote = NoteDatabase.getInstance(it.context)

            GlobalScope.async {
                val delNote = dbNote?.noteDao()?.deleteNote(listNote[position])
                (holder.itemView.context as HomeFragment).activity?.runOnUiThread {
                    (holder.itemView.context as HomeFragment)
                }
            }
            Navigation.findNavController(it).navigate(R.id.homeFragment2)
        }

        holder.binding.btnEditNote.setOnClickListener {
            var edit = Bundle()
            edit.putSerializable("dataedit", listNote[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_editFragment)
        }

    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setData(note : ArrayList<DataNote>) {
        this.listNote = note
    }

}