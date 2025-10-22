package de.manuelwenner.moviejunkie.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.ui.viewmodels.MovieViewModel

/**
 * Composable that displays an app bar and a list of heroes.
 */
@Composable
fun MovieJunkieApp(
    navController: NavHostController = rememberNavController()
) {
    /**
     * Repository und Factory einmal erstellen
     * Verhindert, dass bei jeder Recomposition eine neue Instanz erstellt wird
     * Repository bleibt während der gesamten Lebensdauer der Composable erhalten
     * Wichtig für State-Management und Performance
     */
//    val repository = remember { MovieRepository() }
//    val factory = remember { MovieViewModelFactory(repository) }
//    val viewModel: MovieViewModel = viewModel(factory = factory)
    val viewModel: MovieViewModel = hiltViewModel()

    val backStackEntry by navController.currentBackStackEntryAsState()

    var currentRoute = ""
    currentRoute = backStackEntry?.destination?.route?.substringBefore("/").toString()
    if (currentRoute == "Detail") {
        currentRoute = backStackEntry?.arguments?.getString("movieTitle")
            ?: stringResource(R.string.details_screen)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieTopAppBar(
                title = currentRoute,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MovieJunkieScreen.Home.name
        ) {
            composable(route = MovieJunkieScreen.Home.name) {
                HomeScreen(
                    innerPadding,
                    onListItemClicked = { movie ->
                        navController.navigate("${MovieJunkieScreen.Detail.name}/${movie.title}")
                    },
                    viewModel
                )
            }

            composable(
                route = "${MovieJunkieScreen.Detail.name}/{movieTitle}",
                arguments = listOf(navArgument("movieTitle") { type = NavType.StringType })
            ) { backStackEntry ->
                val movieTitle = backStackEntry.arguments?.getString("movieTitle")
                    ?: stringResource(R.string.unknown_movie)
                MovieDetailScreen(
                    innerPadding,
                    movieTitle = movieTitle,
                    viewModel
                )
            }
        }
    }
}

enum class MovieJunkieScreen {
    Home,
    Detail,
}

/**
 * Composable that displays a Top Bar with an icon and text.
 *
 * @param modifier modifiers to set to this composable
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }

    )
}