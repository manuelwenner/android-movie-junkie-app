package de.manuelwenner.moviejunkie.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.theme.MovieJunkieTheme
import de.manuelwenner.moviejunkie.ui.viewmodels.MovieViewModel

@Composable
fun MovieDetailScreen(innerPadding: PaddingValues, movieTitle: String, viewModel: MovieViewModel) {
    val movieUiState by viewModel.uiState.collectAsState()
    val movie = movieUiState.movies.firstOrNull { it.title == movieTitle } ?: Movie(
        title = "Unknown", rating = 0F, image = R.drawable.movie, imageUrl = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            // --- Hintergrundbild (unscharf & leicht abgedunkelt) ---
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${movie.imageUrl}")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .matchParentSize()
                    .blur(20.dp)
                    .alpha(0.6f),
                placeholder = painterResource(R.drawable.movie),
                error = painterResource(R.drawable.movie)
            )

            // Vordergrundbild (Poster vollständig sichtbar)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/w500${movie.imageUrl}")
                    .crossfade(true)
                    .build(),
                contentDescription = movie.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight()
                    .padding(horizontal = 32.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .shadow(8.dp, RoundedCornerShape(4.dp)),
                placeholder = painterResource(R.drawable.movie),
                error = painterResource(R.drawable.movie)
            )
        }


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