package com.example.imdbpractise.model.responseModels

data class FilmsResponse(
    val results: List<FilmResponse> = emptyList()
)