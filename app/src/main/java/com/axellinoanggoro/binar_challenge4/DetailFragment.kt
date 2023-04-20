package com.axellinoanggoro.binar_challenge4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axellinoanggoro.binar_challenge4.databinding.FragmentDetailBinding
import com.axellinoanggoro.binar_challenge4.room.DataNote
import com.axellinoanggoro.binar_challenge4.room.NoteDatabase

class DetailFragment : Fragment() {

    lateinit var binding : FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var getDetail = arguments?.getSerializable("detail") as DataNote

        binding.detailTitle.text = getDetail.judul
        binding.detailNote.text = getDetail.catatan
    }

}