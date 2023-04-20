package com.axellinoanggoro.binar_challenge4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_challenge4.databinding.FragmentHomeBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    var noteDb : NoteDatabase? = null
    lateinit var adapterNote: AdapterNote
    lateinit var noteViewModel: NoteViewModel
    lateinit var pref: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("data_reg", Context.MODE_PRIVATE)
        binding?.tvUsername?.text = "Hi, " + pref.getString("nama", "name")

        noteDb = NoteDatabase.getInstance(requireContext())

       noteVm()

        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNoteObservers().observe(viewLifecycleOwner, Observer {
            adapterNote.setData(it as ArrayList<DataNote>)
        })

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_addNoteFragment)
        }
    }

    fun noteVm(){
        adapterNote = AdapterNote(ArrayList())
        binding.noteRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.noteRv.adapter = adapterNote
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = noteDb?.noteDao()?.getDataNote()
            activity?.runOnUiThread{
                adapterNote = AdapterNote(data!!)
                binding.noteRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.noteRv.adapter = adapterNote
            }
        }
    }

}