package de.manuelwenner.moviejunkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val image = painterResource(R.drawable.movie)

    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Headline()
            Welcome(
                name = "Manuel",
            )

            Button(
                onClick = {},
                modifier = Modifier.padding(top = 32.dp)
            ) {
                Text("Bild wechseln")
            }
        }

    }
}

@Composable
fun Headline() {
    Text(
        text = "Movie Junkie App",
        fontSize = 32.sp,
    )
}

@Composable
fun Welcome(name: String) {
    Text(text = "Welcome $name to the greatest movie app ever.")
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