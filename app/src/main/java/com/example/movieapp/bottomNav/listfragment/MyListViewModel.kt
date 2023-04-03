package com.example.movieapp.bottomNav.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.MovieEntity
import com.example.movieapp.Repository
import com.example.movieapp.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _movieFromDataBase = MutableStateFlow<State<List<MovieEntity>>>(State.Loading)
    val movieFromDataBase: MutableStateFlow<State<List<MovieEntity>>>
        get() = _movieFromDataBase

    init {
        getMovieList()
    }

    fun getAll(): List<MovieEntity>? {
        return _movieFromDataBase.value.toData()
    }

    fun deleteItemFromList(id: Int): State<List<MovieEntity>> {
        viewModelScope.launch {
            repository.deleteItemFromMoviesList(id)
            _movieFromDataBase.value = repository.getMoviesListFromDataBase()
        }
        return _movieFromDataBase.value
    }

    fun addItemToDataBase(item: MovieEntity) {
        viewModelScope.launch {
            repository.addDataToDataBase(item)
            _movieFromDataBase.value = repository.getMoviesListFromDataBase()
        }

    }

    fun getItemFromDataBase(): MutableStateFlow<State<List<MovieEntity>>> {
        return _movieFromDataBase
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _movieFromDataBase.value = repository.getMoviesListFromDataBase()
        }
    }
}