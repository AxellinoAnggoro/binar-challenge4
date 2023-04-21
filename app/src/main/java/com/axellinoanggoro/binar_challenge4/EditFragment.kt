package com.axellinoanggoro.binar_challenge4

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.axellinoanggoro.binar_challenge4.databinding.FragmentEditBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private var noteDb: NoteDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteDb = NoteDatabase.getInstance(requireContext())

        val getNoteData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("dataedit", DataNote::class.java)
        } else {
            arguments?.getParcelable("dataedit")
        }

        if (getNoteData != null) binding.editTitle.setText(getNoteData.judul)
        if (getNoteData != null) binding.editNote.setText(getNoteData.catatan)
        if (getNoteData != null) binding.editId.setText(getNoteData.id.toString())

        binding.btnEditNote.setOnClickListener {
            val editId = binding.editId.text.toString().toInt()
            val editJudul = binding.editTitle.text.toString()
            val editCatatan = binding.editNote.text.toString()
            GlobalScope.async {
                noteDb?.noteDao()?.updateNote((DataNote(editId, editJudul, editCatatan)))
            }
            Toast.makeText(requireContext(), "Edit Data Success", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editFragment_to_homeFragment2)
        }
    }

}