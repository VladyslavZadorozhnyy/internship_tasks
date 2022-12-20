package com.example.imdbpractise.ui.filmList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdbpractise.bl.films.FilmsManager
import com.example.imdbpractise.model.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmListViewModel(
    private val genreId: Long,
    private val filmManager: FilmsManager
): ViewModel() {
    val adapterFilmsData = MutableLiveData<ArrayList<Film>>(ArrayList())
    val noItemsLeftLiveData = MutableLiveData<Boolean>(false)

    suspend fun loadFilms(page: Int) {
        withContext(Dispatchers.Main) {
            val updatedFilms = adapterFilmsData.value ?: ArrayList()
                updatedFilms.addAll(filmManager.getFilmsByGenre(genreId, page))
                adapterFilmsData.value = updatedFilms

            if (page == 2) {
                noItemsLeftLiveData.value = true
            }
        }
    }
}