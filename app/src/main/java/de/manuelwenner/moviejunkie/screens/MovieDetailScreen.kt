package de.manuelwenner.moviejunkie.screens

import de.manuelwenner.moviejunkie.ui.theme.MovieJunkieTheme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.data.getMovies
import de.manuelwenner.moviejunkie.model.Movie

@Composable
fun MovieDetailScreen(innerPadding: PaddingValues, movieTitle: String) {
    // Movie Data (For now we can mock this data, later we could fetch this from a database)
    val movie = getMovieDetails(movieTitle)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Image(
            painter = painterResource(id = movie.image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth().height(200.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = movie.title,
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Rating: ${movie.rating}/10",
                fontSize = 18.sp,
                color = Color.Gray,
            )
        }
    }
}

fun getMovieDetails(movieTitle: String): Movie {
    return getMovies().firstOrNull { it.title == movieTitle } ?: Movie(
        title = "Unknown", rating = 1F, image = R.drawable.movie
    )
}

@Composable
@Preview
fun MovieDetailPreview() {
    MovieJunkieTheme {
        val movieTitle = getMovies()[7].title

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                MovieDetailScreen(innerPadding, movieTitle)
            }
        }
    }
}