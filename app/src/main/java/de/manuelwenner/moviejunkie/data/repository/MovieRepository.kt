package de.manuelwenner.moviejunkie.data.repository

import de.manuelwenner.moviejunkie.data.network.RetrofitInstance


class MovieRepository {
    suspend fun fetchPopularMovies(): String {
        return try {
            val response =
                RetrofitInstance.api.getDiscoverMovie()
            response
        } catch (e: Exception) {
            e.printStackTrace()
           ""
        }
    }
}