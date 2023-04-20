package com.axellinoanggoro.binar_challenge4

import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axellinoanggoro.binar_challenge4.databinding.FragmentAddNoteBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteFragment : Fragment() {
    lateinit var binding:FragmentAddNoteBinding
    var noteDb : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDb = NoteDatabase.getInstance(requireContext())

        binding.btnSaveNote.setOnClickListener {
            addData()
        }
    }

    fun addData(){
        GlobalScope.async {
            var judul = binding.noteTitle.text.toString()
            var catatan = binding.noteContent.text.toString()

            noteDb?.noteDao()?.insertData(DataNote(0, judul, catatan))
        }
        findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment2)
    }


}