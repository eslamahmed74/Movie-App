package com.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.SmallLayoutMovieDetailBinding
import com.example.movieapp.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var title: String
    private lateinit var imageUrl: String
    private lateinit var overView: String
    private lateinit var binding: SmallLayoutMovieDetailBinding
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
        binding.movieTitleSmallDetail.text=title
        setImageFromUrl(imageUrl,binding.movieImageSmallDetail)
        binding.movieDescriptionSmallDetail.text=overView
    }

    private fun setImageFromUrl(imgUrl:String,view: ImageView){
        Glide.with(view).load(Constants.IMAGE_URL + imgUrl).into(view)
    }
}