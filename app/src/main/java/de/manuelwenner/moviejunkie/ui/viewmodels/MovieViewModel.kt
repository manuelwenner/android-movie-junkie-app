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
import retrofit2.HttpException
import java.io.IOException
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
        loadMovies()
    }

    fun loadMovies() {
        viewModelScope.launch {
            // Loading starten und alte Fehler löschen
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val movieDtos = repository.fetchPopularMovies()
                val newMovies = movieDtos.map { it.toUiModel() }
                _uiState.update { it.copy(movies = newMovies, isLoading = false) }
            } catch (e: HttpException) {
                val errorMessage = when (e.code()) {
                    401 -> "API Key ungültig oder abgelaufen"
                    403 -> "Zugriff verweigert"
                    404 -> "API Endpoint nicht gefunden"
                    429 -> "Zu viele Anfragen - bitte später versuchen"
                    500 -> "Server Fehler"
                    else -> "HTTP Fehler: ${e.code()}"
                }
                _uiState.update { it.copy(
                    errorMessage = errorMessage,
                    isLoading = false
                )}
            } catch (e: IOException) {
                _uiState.update { it.copy(
                    errorMessage = "Netzwerk Fehler - bitte Internetverbindung prüfen",
                    isLoading = false
                )}
            } catch (e: Exception) {
                _uiState.update { it.copy(
                    errorMessage = "Unbekannter Fehler: ${e.message}",
                    isLoading = false
                )}
            }
        }
    }
}