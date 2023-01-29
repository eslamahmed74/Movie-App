package com.example.movieapp.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
    private var auth = FirebaseAuth.getInstance()

    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        binding.tvLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.btnFragmentSignup.setOnClickListener { v ->
            auth.createUserWithEmailAndPassword(
                binding.edSignupEmail.text.toString(),
                binding.edSignupPassword.text.toString()
            ).addOnSuccessListener {
                Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_mainFragment)
            }.addOnFailureListener {
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
            }
        }

        binding.btnSignuoGoogle.setOnClickListener {

        }
        binding.btnFragmentSignup
        return binding.root
    }


}