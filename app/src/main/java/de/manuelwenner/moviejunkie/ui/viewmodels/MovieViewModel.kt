package de.manuelwenner.moviejunkie.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.MoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    // immutable and only mutable inside the ViewModel
    private val _uiState = MutableStateFlow(MoviesUiState())

    // the asStateFlow() makes this mutable state flow a read-only state flow.
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        // Synchron, kein Launch nötig da getMovies() nicht suspendierend ist
        _uiState.update { it.copy(movies = getMovies()) }
    }

    private fun getMovies(): List<Movie> {
        return listOf(
            Movie("The Dark Knight", 9.1F, R.drawable.batman),
            Movie("Inception", 8.8F),
            Movie("The Godfather", 9.5F),
            Movie("Interstellar", 8.2F),
            Movie("Pulp Fiction", 7.9F),
            Movie("The Shawshank Redemption", 6.2F),
        )
    }

    fun loadMovies() {
        viewModelScope.launch {
            // Loading starten und alte Fehler löschen
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val newMovies = fetchMoviesFromRepository()
                val updatedMovies = _uiState.value.movies + newMovies
                _uiState.update { it.copy(movies = updatedMovies, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    errorMessage = e.message ?: "Unbekannter Fehler",
                    isLoading = false
                )}
            }
        }
    }

    private fun fetchMoviesFromRepository(): List<Movie> {
        // Simulierte Netzwerk- oder Datenbankabfrage
        return listOf(
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

}