package com.example.imdbpractise.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbpractise.bl.genres.GenresManager
import com.example.imdbpractise.model.Genre
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val genresManager: GenresManager
) : ViewModel() {
    val genresLiveData = MutableLiveData<List<Genre>>(emptyList())

    fun getAllGenres() {
        viewModelScope.launch(Dispatchers.Main) {
            genresLiveData.value = genresManager.getAllGenres()
        }
    }

}