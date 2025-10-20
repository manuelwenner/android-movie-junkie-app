package de.manuelwenner.moviejunkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.components.MovieItem
import de.manuelwenner.moviejunkie.ui.theme.MovieJunkieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieJunkieTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    HomeScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    val images = listOf(
        R.drawable.movie,
        R.drawable.cinema,
        R.drawable.popcorn
    )

    var currentImageIndex by remember { mutableIntStateOf(0) }

    val movies = listOf(
        Movie("The Dark Knight", 9.1F),
        Movie("Inception", 8.8F),
        Movie("The Godfather", 9.5F),
        Movie("Interstellar", 8.2F),
        Movie("Pulp Fiction", 7.9F),
        Movie("The Shawshank Redemption", 6.2F),
        Movie("Forrest Gump", 9F),
        Movie("The Matrix", 8.8F),
        Movie("Fight Club", 9.1F),
        Movie("Gladiator", 7.8F),
        Movie("Gladiator 2", 5.5F),
        Movie("The Lord of the Rings: The Return of the King", 9F),
        Movie("Schindler's List", 9F),
        Movie("The Green Mile", 8.6F)
    )

    Box {
        Image(
            painter = painterResource(images[currentImageIndex]),
            contentDescription = "Hintergrundbild",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Headline()
            Welcome(name = "Manuel")

            Button(
                onClick = {
                    currentImageIndex = (currentImageIndex + 1) % images.size
                },
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text(stringResource(R.string.change_image_button))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies) { movie ->
                    MovieItem(movie)
                }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieJunkieTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeScreen(innerPadding)
        }
    }
}