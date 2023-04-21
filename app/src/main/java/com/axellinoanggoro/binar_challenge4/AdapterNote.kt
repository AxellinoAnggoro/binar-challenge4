package com.axellinoanggoro.binar_challenge4


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_challenge4.databinding.NoteItemBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterNote(private var listNote: List<DataNote>) :
    RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    private var dbNote: NoteDatabase? = null

    class ViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.noteItem = listNote[position]

        holder.binding.btnDeleteNote.setOnClickListener {
            dbNote = NoteDatabase.getInstance(it.context)

            GlobalScope.async {
                dbNote?.noteDao()?.deleteNote(listNote[position])
                (holder.itemView.context as HomeFragment).activity?.runOnUiThread {
                    (holder.itemView.context as HomeFragment)
                }
            }
            Toast.makeText(it.context, "Delete Note Success", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).navigate(R.id.homeFragment2)
        }

//        holder.binding.btnEditNote.setOnClickListener {
//            val edit = Bundle()
//            edit.putSerializable("dataedit", listNote[position])
//
//            Toast.makeText(it.context, "Edit Note Success", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).navigate(R.id.action_homeFragment2_to_editFragment)
//        }

//        holder.binding.btnEditNote.setOnClickListener {
//            var move = Intent(it.context, EditFragment::class.java )
//            move.putExtra("dataedit", listNote[position])
//            it.context.startActivity(move)
//        }

        holder.binding.btnEditNote.setOnClickListener {
            val edit = Bundle()
            edit.putParcelable("dataedit", listNote[position])
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment2_to_editFragment, edit)
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setData(note: ArrayList<DataNote>) {
        this.listNote = note
    }

}