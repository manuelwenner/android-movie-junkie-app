package de.manuelwenner.moviejunkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.manuelwenner.moviejunkie.screens.HomeScreen
import de.manuelwenner.moviejunkie.screens.MovieJunkieApp
import de.manuelwenner.moviejunkie.screens.MovieJunkieScreen
import de.manuelwenner.moviejunkie.ui.theme.MovieJunkieTheme
import de.manuelwenner.moviejunkie.ui.viewmodels.MovieViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieJunkieTheme {
                MovieJunkieApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieJunkieTheme {
        val navController = rememberNavController()
        val viewModel: MovieViewModel = hiltViewModel()

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            HomeScreen(
                innerPadding,
                onListItemClicked = { navController.navigate(MovieJunkieScreen.Detail.name) },
                viewModel
            )

        }
    }
}