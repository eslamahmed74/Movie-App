package com.example.movieapp.bottomNav.listfragment

import android.widget.BaseAdapter
import android.widget.ExpandableListView.OnChildClickListener
import com.example.local.MovieEntity
import com.example.movieapp.R
import com.example.movieapp.utils.BaseClickListener

class MyListAdapter( list: List<MovieEntity>): com.example.movieapp.utils.BaseAdapter<MovieEntity>(list) {
    override val layoutId: Int
        get() = R.layout.mylist_item
}