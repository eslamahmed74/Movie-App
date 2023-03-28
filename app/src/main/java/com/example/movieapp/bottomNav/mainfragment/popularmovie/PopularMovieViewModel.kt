package com.example.movieapp.bottomNav.mainfragment.popularmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Repository
import com.example.movieapp.utils.State
import com.example.network.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(private val repository:Repository) : ViewModel() {

//    private val _popularMovieLiveData = MutableLiveData<MovieResponse>()
//    val popularMovieLiveData: LiveData<MovieResponse>
//        get() = _popularMovieLiveData

    private val _popularMovieLiveData = MutableStateFlow<State<MovieResponse?>>(State.Loading)
    val popularMovieLiveData: StateFlow<State<MovieResponse?>>
        get() = _popularMovieLiveData

    private val _topRatedMovieStateFlow = MutableStateFlow<State<MovieResponse?>>(State.Loading)
    val topRatedMovieStateFlow: StateFlow<State<MovieResponse?>>
        get() = _topRatedMovieStateFlow

    private val _upComingMovie = MutableStateFlow<State<MovieResponse?>>(State.Loading)
    val upComingMovie: StateFlow<State<MovieResponse?>>
        get() = _upComingMovie

    private val _tvPopular = MutableStateFlow<State<MovieResponse?>>(State.Loading)
    val tvPopular: StateFlow<State<MovieResponse?>>
        get() = _upComingMovie

    private fun getMovies() {
        viewModelScope.launch {

            async {
                repository.getPopularMovie().collect {
                    _popularMovieLiveData.value = it
                }
            }
            async {
                repository.getTopRatedMovie().collect {
                    _topRatedMovieStateFlow.value = it
                }
            }

            async {
                repository.getUpComingMovie().collect {
                    _upComingMovie.value = it
                }
            }

            async {
                repository.getTvPopular().collect{
                    _tvPopular.value=it
                }
            }
        }
    }

    init {
        getMovies()
    }
}
