package de.manuelwenner.moviejunkie.screens

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.theme.MovieJunkieTheme
import de.manuelwenner.moviejunkie.ui.viewmodels.MovieViewModel

@Composable
fun MovieDetailScreen(innerPadding: PaddingValues, movieTitle: String, viewModel: MovieViewModel) {
    val movieUiState by viewModel.uiState.collectAsState()
    val movie = movieUiState.movies.firstOrNull { it.title == movieTitle } ?: Movie(
        title = "Unknown", rating = 0F, image = R.drawable.movie
    )

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

@Composable
@Preview
fun MovieDetailPreview() {
    MovieJunkieTheme {
        val movieTitle = "Guardians of the galaxy"
        val viewModel: MovieViewModel = viewModel()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                MovieDetailScreen(innerPadding, movieTitle, viewModel)
            }
        }
    }
}