package com.example.movieapp.bottomNav.mainfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BottomSheetFragment
import com.example.movieapp.Movie
import com.example.movieapp.R
import com.example.movieapp.utils.RecyclerViewInteractionListener
import com.example.movieapp.bottomNav.mainfragment.popularmovie.MovieAdapter
import com.example.movieapp.bottomNav.mainfragment.popularmovie.PopularMovieViewModel
import com.example.movieapp.databinding.FragmentMainBinding
import com.example.movieapp.network.MovieResponse
import com.example.movieapp.network.Result

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
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager2 =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager3 =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        setRecyclerView(binding.recyclerView, layoutManager)
        setRecyclerView(binding.recyclerViewtopRated, layoutManager2)
        binding.recyclerViewUpcoming?.let { setRecyclerView(it, layoutManager3) }
        binding.recyclerViewTvpopular?.let {
            setRecyclerView(
                it,
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            )
        }
        return binding.root
    }

    override fun <T> onClickItem(view: T) {
        view as Movie
        Toast.makeText(requireActivity(),view.image,Toast.LENGTH_SHORT).show()
    }

    private fun setRecyclerView(view:RecyclerView,layoutManager: LinearLayoutManager){
        view.layoutManager=layoutManager
        var adapter=MovieAdapter(mutableListOf())
        adapter.setOnItemClickListener {
            var bundle=Bundle()
            bundle.putString("movieTitle",it.title)
            bundle.putString("imageUrl",it.posterPath)
            bundle.putString("movieDescription",it.overview)
            Toast.makeText(requireContext(), it.title,Toast.LENGTH_SHORT).show()
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.arguments=bundle
            bottomSheetFragment.show(requireActivity().supportFragmentManager, bottomSheetFragment.tag)
        }
        view.adapter=adapter
    }
}