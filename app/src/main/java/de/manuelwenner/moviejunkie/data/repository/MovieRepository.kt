package de.manuelwenner.moviejunkie.data.repository

import de.manuelwenner.moviejunkie.data.network.MovieApiService
import de.manuelwenner.moviejunkie.data.network.MovieDto
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: MovieApiService
) : IMovieRepository {
    override suspend fun fetchPopularMovies(): List<MovieDto> {
        return try {
            val response =
                apiService.getDiscoverMovie()
            response.results
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}