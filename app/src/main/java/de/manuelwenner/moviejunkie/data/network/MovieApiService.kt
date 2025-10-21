package de.manuelwenner.moviejunkie.data.network

import retrofit2.http.GET

interface MovieApiService {
    @GET("discover/movie")
    suspend fun getDiscoverMovie(): String
}