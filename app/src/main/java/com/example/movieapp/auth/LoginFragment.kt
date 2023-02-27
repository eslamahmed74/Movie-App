package com.example.movieapp.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val auth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.tvSignup.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnFragmentLogin.setOnClickListener { v ->
//            binding.progressLogin.visibility=View.VISIBLE
//            auth.signInWithEmailAndPassword(
//                binding.edLoginEmail.text.toString(),
//                binding.edLoginPassword.text.toString()
//            ).addOnSuccessListener {
//                binding.progressLogin.visibility=View.GONE
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_mainFragment)
//            }.addOnFailureListener {
//                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
//                binding.progressLogin.visibility=View.GONE
//            }
        }
        return binding.root
    }


}