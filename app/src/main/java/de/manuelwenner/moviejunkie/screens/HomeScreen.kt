package de.manuelwenner.moviejunkie.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.components.MovieItem
import de.manuelwenner.moviejunkie.ui.viewmodels.MovieViewModel

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    onListItemClicked: (Movie) -> Unit,
    viewModel: MovieViewModel
) {
    val movieUiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(innerPadding)) {
        val images = listOf(
            painterResource(R.drawable.movie),
            painterResource(R.drawable.default_item),
            painterResource(R.drawable.cinema),
        )
        var currentImage by remember { mutableStateOf(images.random()) }


        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = currentImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                HomeSection(
                    modifier = Modifier.padding(8.dp),
                    onClick = { currentImage = images.random() }
                )

                Text(text = "" + movieUiState.errorMessage)

                MovieList(modifier = Modifier.weight(1f), onListItemClicked, movieUiState.movies)
            }
        }
    }
}

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Headline()
        Welcome("Manuel")
        Button(
            onClick = onClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(stringResource(R.string.change_image_button))
        }
    }
}

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    onListItemClicked: (Movie) -> Unit,
    movies: List<Movie>
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(movies) { movie ->
                MovieItem(movie) { onListItemClicked(movie) }
            }

        }
    }
}

@Composable
fun Headline() {
    Box(
        modifier = Modifier
            .background(
                color = Color.Black.copy(alpha = 0.6f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.headline),
            fontSize = 32.sp,
            color = Color.White
        )
    }
}

@Composable
fun Welcome(name: String) {
    Text(
        text = stringResource(R.string.welcome_message, name),
        color = Color.White
    )
}