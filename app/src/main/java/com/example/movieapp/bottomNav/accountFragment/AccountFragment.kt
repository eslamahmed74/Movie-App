package com.example.movieapp.bottomNav.accountFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private lateinit var binding:FragmentAccountBinding
    private val viewModel:SettingListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        val layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.recyclerSetting.layoutManager=layoutManager
        binding.recyclerSetting.adapter=ListSettingAdapter(emptyList())
        return binding.root
    }
}