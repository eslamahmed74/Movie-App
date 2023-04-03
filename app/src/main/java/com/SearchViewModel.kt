package com

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.local.MovieEntity
import com.example.movieapp.Repository
import com.example.movieapp.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val _searchFilterStateFlow = MutableStateFlow("")
    private val _movieFromDataBase = MutableStateFlow<State<List<MovieEntity>>>(State.Loading)

    private val _filterdMovieList = MutableStateFlow<State<List<MovieEntity>>>(State.Loading)
    val filterdMovieList: StateFlow<State<List<MovieEntity>>>
        get() = _filterdMovieList

    init {
        getMovieList()
    }

    fun search(): State<List<MovieEntity>> {
        viewModelScope.launch(Dispatchers.IO) {
            _filterdMovieList.value = repository.searchQuery(_searchFilterStateFlow.value)
        }
        return _filterdMovieList.value
    }

    fun searchFunctionality(): StateFlow<State<List<MovieEntity>>> {
        val list = mutableListOf<MovieEntity>()
        var index = 0
        for (item in _movieFromDataBase.value.toData()!!) {
            if (item.name.contains(_searchFilterStateFlow.value, ignoreCase = true)) {
                list.add(item)
            }
            Log.e("TAG", filterdMovieList.value.toData().toString())
        }
        _filterdMovieList.value = State.Success(list)
        return filterdMovieList
    }

    private fun getMovieList() {
        viewModelScope.launch {
            _movieFromDataBase.value = repository.getMoviesListFromDataBase()
        }
    }
}