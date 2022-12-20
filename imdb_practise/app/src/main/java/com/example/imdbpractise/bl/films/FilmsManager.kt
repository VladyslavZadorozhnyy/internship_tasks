package com.example.imdbpractise.bl.films

import com.example.imdbpractise.model.Film

interface FilmsManager {
    suspend fun getFilm(filmId: Long): Film
    suspend fun getFilmsByGenre(genreId: Long, page: Int = 1): List<Film>
}