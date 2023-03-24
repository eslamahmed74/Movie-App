package com.example.movieapp.bottomNav.listfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentListBinding
import com.example.movieapp.databinding.MylistItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding:FragmentListBinding
    private val viewModel by activityViewModels<MyListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)
        binding.lifecycleOwner=this
        binding.listViewModel=viewModel
        val layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.movieListRecyclyer.layoutManager=layoutManager
        binding.movieListRecyclyer.adapter=MyListAdapter(emptyList())
        return binding.root
    }

}