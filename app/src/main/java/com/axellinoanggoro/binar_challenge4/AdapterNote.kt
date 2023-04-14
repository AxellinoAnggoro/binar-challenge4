package com.axellinoanggoro.binar_challenge4

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_challenge4.databinding.NoteItemBinding

class AdapterNote(var listNote : ArrayList<DataNote>) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {
    class ViewHolder(val binding : NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindNotes(itemNotes : DataNote){
            binding.note = itemNotes
            binding.noteCv.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("key", itemNotes)
//              navigation
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNote.ViewHolder {
        var view = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterNote.ViewHolder, position: Int) {
        holder.bindNotes(listNote[position])
        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putSerializable("BUNDEL", listNote[position])

        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setData(list : ArrayList<DataNote>){
        this.listNote.addAll(list)
    }

    interface onItemClickListener{
        fun onItemClick(nama : String)
    }
}