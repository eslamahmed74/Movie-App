package com.example.movieapp.bottomNav.mainfragment.popularmovie

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Repository
import com.example.movieapp.State
import com.example.movieapp.network.MovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularMovieViewModel() : ViewModel() {

    private val repository = Repository()

//    private val _popularMovieLiveData = MutableLiveData<MovieResponse>()
//    val popularMovieLiveData: LiveData<MovieResponse>
//        get() = _popularMovieLiveData

    private val _popularMovieLiveData = MutableStateFlow<State<MovieResponse?>>(State.Loading)
    val popularMovieLiveData: StateFlow<State<MovieResponse?>>
        get() = _popularMovieLiveData
    private fun getPopularMovie() {
        viewModelScope.launch {
            repository.getPopularMovie().collect {
                _popularMovieLiveData.value=it
               // Log.e("TAG","2"+_popularMovieLiveData.value.toString())
            }
        }
    }

    init {
        getPopularMovie()
    }
}
