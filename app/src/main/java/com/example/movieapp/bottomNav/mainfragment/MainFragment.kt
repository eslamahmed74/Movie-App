package com.example.movieapp.bottomNav.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BottomSheetFragment
import com.example.movieapp.R
import com.example.movieapp.utils.RecyclerViewInteractionListener
import com.example.movieapp.bottomNav.mainfragment.popularmovie.MovieAdapter
import com.example.movieapp.bottomNav.mainfragment.popularmovie.PopularMovieViewModel
import com.example.movieapp.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        binding.btnRetry?.setOnClickListener {
        lifecycleScope.launch(Dispatchers.IO){
                viewModel.getMovies()
            }
        }

        setRecyclerView(
            binding.recyclerView,
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        )
        setRecyclerView(
            binding.recyclerViewtopRated,
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        )
        binding.recyclerViewUpcoming?.let {
            setRecyclerView(
                it,
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            )
        }

        return binding.root
    }

    override fun <T> onClickItem(view: T) {
//        view as Movie
//        Toast.makeText(requireActivity(),view.image,Toast.LENGTH_SHORT).show()
    }

    private fun setRecyclerView(view: RecyclerView, layoutManager: LinearLayoutManager) {
        view.layoutManager = layoutManager
        var adapter = MovieAdapter(mutableListOf())
        adapter.setOnItemClickListener {
            var bundle = Bundle()
            bundle.putString("movieTitle", it.title)
            bundle.putString("imageUrl", it.posterPath)
            bundle.putString("movieDescription", it.overview)
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.arguments = bundle
            bottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                bottomSheetFragment.tag
            )
        }
        view.adapter = adapter
    }
}