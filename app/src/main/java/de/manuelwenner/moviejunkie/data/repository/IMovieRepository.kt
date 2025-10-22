package de.manuelwenner.moviejunkie.data.repository

import de.manuelwenner.moviejunkie.data.network.MovieDto

interface IMovieRepository {
    suspend fun fetchPopularMovies(): List<MovieDto>
}