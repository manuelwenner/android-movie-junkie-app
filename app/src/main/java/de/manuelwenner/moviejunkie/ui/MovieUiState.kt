package de.manuelwenner.moviejunkie.ui

import de.manuelwenner.moviejunkie.model.Movie

data class MoviesUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null
)