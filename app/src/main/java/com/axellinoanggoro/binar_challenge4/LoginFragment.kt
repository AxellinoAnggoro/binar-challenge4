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
import com.axellinoanggoro.binar_challenge4.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var dataPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataPref = requireContext().getSharedPreferences("data_reg", Context.MODE_PRIVATE)

        binding.loginBtn.setOnClickListener {
            var getUsername = dataPref.getString("username", "")
            var getPassword = dataPref.getString("password", "")

            var checkUsername = binding.loginUsername.text.toString()
            var checkPassword = binding.loginPassword.text.toString()

            if(checkUsername == getUsername && checkPassword == getPassword){
                Navigation.findNavController(view).navigate(R.id.action_loginFragment3_to_homeFragment2)
                Toast.makeText(context, "Login Success", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context, "Login Failed", Toast.LENGTH_LONG).show()
            }
        }
        binding.loginReg.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment3_to_registerFragment2)
        }
    }
}