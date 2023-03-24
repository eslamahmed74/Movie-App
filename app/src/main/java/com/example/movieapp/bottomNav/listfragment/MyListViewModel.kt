package com.example.movieapp.bottomNav.listfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.MovieEntity
import com.example.movieapp.Repository
import com.example.movieapp.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieFromDataBase = MutableLiveData<List<MovieEntity>>()
    val movieFromDataBase: LiveData<List<MovieEntity>>
        get() = _movieFromDataBase

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _movieFromDataBase.value = repository.getMoviesList().value
        }
        Log.e("TAG1", _movieFromDataBase.value.toString())
    }
}