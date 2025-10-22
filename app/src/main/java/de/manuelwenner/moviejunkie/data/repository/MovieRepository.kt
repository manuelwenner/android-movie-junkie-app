package de.manuelwenner.moviejunkie.data.repository

import de.manuelwenner.moviejunkie.data.network.MovieDto
import de.manuelwenner.moviejunkie.data.network.RetrofitInstance


class MovieRepository {
    suspend fun fetchPopularMovies(): List<MovieDto> {
        return try {
            val response =
                RetrofitInstance.api.getDiscoverMovie()
            response.results
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}