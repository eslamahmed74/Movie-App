package com.example.movieapp.bottomNav.accountFragment

import androidx.lifecycle.ViewModel
import com.example.movieapp.Repository
import com.example.movieapp.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SettingListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _settingDataStateFlow = MutableStateFlow<State<List<SettingData>>>(State.Loading)
    val settingDataStateFlow: StateFlow<State<List<SettingData>>>
        get() = _settingDataStateFlow

    init {
        getData()
    }

    private fun getData() {
        _settingDataStateFlow.value = repository.getSettingDataItem()
    }
}