package com.example.movieapp.bottomNav.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewInteractionListener
import com.example.movieapp.bottomNav.mainfragment.popularmovie.PopularMovieAdapter
import com.example.movieapp.bottomNav.mainfragment.popularmovie.PopularMovieViewModel
import com.example.movieapp.databinding.FragmentMainBinding

class MainFragment : Fragment(), RecyclerViewInteractionListener {
    private lateinit var binding: FragmentMainBinding
    private val viewModel by activityViewModels<PopularMovieViewModel>()

    @OptIn(ExperimentalStdlibApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewmodel=viewModel
        binding.lifecycleOwner=this

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = PopularMovieAdapter(mutableListOf(), this)
        return binding.root
    }

    override fun <T> onClickItem(view: T) {

    }

}