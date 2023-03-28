package com.example.movieapp.bottomNav.mainfragment.popularmovie

import com.example.movieapp.utils.BaseAdapter
import com.example.movieapp.utils.BaseClickListener
import com.example.movieapp.Movie
import com.example.movieapp.R
import com.example.network.MovieResponse
import com.example.network.Result

/*
view.adapter
 , clickListener
 */
class MovieAdapter(
    list: List<Result>

    ) : BaseAdapter<Result>(list) {
    override val layoutId: Int = R.layout.movie_item_popular
}