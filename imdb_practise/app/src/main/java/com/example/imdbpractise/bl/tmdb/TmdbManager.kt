package com.example.imdbpractise.bl.tmdb

import com.example.imdbpractise.model.Film
import com.example.imdbpractise.model.Genre

interface TmdbManager {
    suspend fun getGenres(): List<Genre>
    suspend fun getFilmsList(genreId: Long, page: Int = 1): List<Film>
    suspend fun getFilmInfo(id: Long): Film
}