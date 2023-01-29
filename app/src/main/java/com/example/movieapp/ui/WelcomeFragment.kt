package com.example.movieapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding:FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false)
        binding.btnLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        binding.btnSignup.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomeFragment_to_signUpFragment)
        }
        return binding.root
    }


}