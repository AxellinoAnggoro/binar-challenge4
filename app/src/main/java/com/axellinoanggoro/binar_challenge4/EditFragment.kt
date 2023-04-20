package com.axellinoanggoro.binar_challenge4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axellinoanggoro.binar_challenge4.databinding.FragmentEditBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {

    lateinit var binding : FragmentEditBinding
    var noteDb : NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDb = NoteDatabase.getInstance(requireContext())

        var getData = arguments?.getSerializable("dataedit") as DataNote
        binding.idNote.setText(getData.id.toString())
        binding.editTitle.setText(getData.judul)
        binding.editNote.setText(getData.catatan)

        binding.btnEditNote.setOnClickListener {
            var editId = binding.idNote.text.toString().toInt()
            var editJudul = binding.editTitle.text.toString()
            var editCatatan = binding.editNote.text.toString()
            GlobalScope.async {
                var editNote = noteDb?.noteDao()?.updateNote((DataNote(editId, editJudul, editCatatan)))
            }
            findNavController().navigate(R.id.action_editFragment_to_homeFragment2)
        }
    }
}