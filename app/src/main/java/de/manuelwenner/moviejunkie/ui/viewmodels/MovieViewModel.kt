package de.manuelwenner.moviejunkie.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.manuelwenner.moviejunkie.R
import de.manuelwenner.moviejunkie.data.network.toUiModel
import de.manuelwenner.moviejunkie.data.repository.IMovieRepository
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.MoviesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: IMovieRepository
) : ViewModel() {

    // immutable and only mutable inside the ViewModel
    private val _uiState = MutableStateFlow(MoviesUiState())

    // the asStateFlow() makes this mutable state flow a read-only state flow.
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()

    init {
        // Synchron, kein Launch nötig da getMovies() nicht suspendierend ist
        _uiState.update { it.copy(movies = getMovies()) }

        loadMovies()
    }

    private fun getMovies(): List<Movie> {
        return listOf(
            Movie("The Dark Knight", 9.1F, R.drawable.batman, ""),
        )
    }

    fun loadMovies() {
        viewModelScope.launch {
            // Loading starten und alte Fehler löschen
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
//                val newMovies = fetchMoviesFromRepository()
                val movieDtos = repository.fetchPopularMovies()
                val newMovies = movieDtos.map { it.toUiModel() }
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
}