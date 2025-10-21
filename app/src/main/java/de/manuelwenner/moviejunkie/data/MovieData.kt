package de.manuelwenner.moviejunkie.data

import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.model.Movie

fun getMovies(): List<Movie> {
    return listOf(
        Movie("The Dark Knight", 9.1F, R.drawable.batman),
        Movie("Inception", 8.8F),
        Movie("The Godfather", 9.5F),
        Movie("Interstellar", 8.2F),
        Movie("Pulp Fiction", 7.9F),
        Movie("The Shawshank Redemption", 6.2F),
        Movie("Forrest Gump", 9F),
        Movie("The Matrix", 8.8F),
        Movie("Guardians of the galaxy", 9.1F, R.drawable.guardians),
        Movie("Gladiator", 7.8F),
        Movie("Gladiator 2", 5.5F),
        Movie("The Lord of the Rings: The Return of the King", 9F),
        Movie("Schindler's List", 9F),
        Movie("Transformers", 8.6F, R.drawable.transformers)
    )
}