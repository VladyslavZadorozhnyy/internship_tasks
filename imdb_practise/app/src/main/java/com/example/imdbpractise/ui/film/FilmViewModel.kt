package com.example.imdbpractise.ui.film

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbpractise.bl.films.FilmsManager
import com.example.imdbpractise.model.Film
import kotlinx.coroutines.launch

class FilmViewModel(
    private val filmId: Long,
    private val filmsManager: FilmsManager
): ViewModel() {
    val filmLiveData = MutableLiveData<Film>()

    init {
        viewModelScope.launch {
            filmLiveData.value = filmsManager.getFilm(filmId)
        }
    }

}