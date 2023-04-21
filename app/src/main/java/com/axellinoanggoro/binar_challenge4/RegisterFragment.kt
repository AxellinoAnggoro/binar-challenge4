package com.axellinoanggoro.binar_challenge4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.axellinoanggoro.binar_challenge4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireContext().getSharedPreferences("data_reg", Context.MODE_PRIVATE)

        binding.regBtn.setOnClickListener {
            val name = binding.regName.text.toString()
            val username = binding.regUsername.text.toString()
            val password = binding.regPassword.text.toString()

            val regData = pref.edit()
            regData.putString("nama", name)
            regData.putString("username", username)
            regData.putString("password", password)
            regData.apply()
            Toast.makeText(context, "Register Success", Toast.LENGTH_LONG).show()
            Navigation.findNavController(view)
                .navigate(R.id.action_registerFragment2_to_loginFragment3)
        }

        binding.regLogin.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_registerFragment2_to_loginFragment3)
        }
    }

}