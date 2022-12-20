package com.example.imdbpractise.bl.genres

import com.example.imdbpractise.model.Genre

interface GenresManager {
    suspend fun getAllGenres(): List<Genre>
}