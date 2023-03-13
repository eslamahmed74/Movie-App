package com.example.movieapp.bottomNav.mainfragment.popularmovie

import com.example.movieapp.BaseAdapter
import com.example.movieapp.BaseClickListener
import com.example.movieapp.Movie
import com.example.movieapp.R

class MovieAdapter(
    list: List<Movie>, clickListener: BaseClickListener,

    ) : BaseAdapter<Movie>(list, clickListener) {
    override val layoutId: Int = R.layout.movie_item_popular
}