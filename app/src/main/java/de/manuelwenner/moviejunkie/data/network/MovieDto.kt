package de.manuelwenner.moviejunkie.data.network

import com.squareup.moshi.Json
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.model.Movie

data class MovieResponse(
    val results: List<MovieDto>
)

data class MovieDto(
    val title: String,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "poster_path") val imageUrl: String
)

fun MovieDto.toUiModel(): Movie {
    return Movie(
        title = this.title,
        rating = this.rating.toFloat(),
        image = R.drawable.movie,
        imageUrl = this.imageUrl
    )
}