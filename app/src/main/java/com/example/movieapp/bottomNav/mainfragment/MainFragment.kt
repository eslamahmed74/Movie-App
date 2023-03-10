package com.example.movieapp.bottomNav.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.RecyclerViewInteractionListener
import com.example.movieapp.bottomNav.mainfragment.popularmovie.MovieAdapter
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
        val layoutManager2 = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        setRecyclerView(binding.recyclerView,layoutManager)
        setRecyclerView(binding.recyclerViewtopRated,layoutManager2)
        return binding.root
    }

    override fun <T> onClickItem(view: T) {

    }

    private fun setRecyclerView(view:RecyclerView,layoutManager: LinearLayoutManager){
        view.layoutManager=layoutManager
        view.adapter=MovieAdapter(mutableListOf(),this)
    }
}