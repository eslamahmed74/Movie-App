package com.example

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.local.MovieEntity
import com.example.local.MovieListDataBase
import com.example.movieapp.R
import com.example.movieapp.bottomNav.listfragment.MyListViewModel
import com.example.movieapp.databinding.SmallLayoutMovieDetailBinding
import com.example.movieapp.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var title: String
    private lateinit var imageUrl: String
    private lateinit var overView: String
    private lateinit var binding: SmallLayoutMovieDetailBinding
    private val viewModel: MyListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        title = arguments?.getString("movieTitle") ?: ""
        imageUrl = arguments?.getString("imageUrl") ?: ""
        overView = arguments?.getString("movieDescription") ?: ""
        binding =
            DataBindingUtil.inflate(inflater, R.layout.small_layout_movie_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.movieTitleSmallDetail.text = title
        setImageFromUrl(imageUrl, binding.movieImageSmallDetail)
        binding.movieDescriptionSmallDetail.text = overView
        binding.btnMylistSmallDetail.setOnClickListener {
            insertData(
                MovieEntity(
                    name = title,
                    imgUrl = imageUrl,
                    overView = overView
                )
            )
        }

    }

    private fun insertData(item: MovieEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            var database = MovieListDataBase.createRoomInstance(activity?.applicationContext!!)
            viewModel.addItemToDataBase(
                item
            )
            withContext(Dispatchers.Main) {
                Toast.makeText(requireContext(), "added", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setImageFromUrl(imgUrl: String, view: ImageView) {
        Glide.with(view).load(Constants.IMAGE_URL + imgUrl).into(view)
    }
}