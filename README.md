# Movie Junkie App

Eine Android-App zur Darstellung beliebter Filme, entwickelt mit Jetpack Compose und modernen Android-Architektur-Patterns.

## 📱 Über die App

Die Movie Junkie App ist eine praktische Lernanwendung für Android-Entwicklung, die verschiedene moderne Konzepte und Technologien demonstriert. Die App zeigt eine Liste beliebter Filme an und ermöglicht es Benutzern, Details zu einzelnen Filmen zu betrachten.

### Hauptfunktionen
- **Filmübersicht**: Anzeige einer Liste beliebter Filme mit Titel, Bewertung und Poster
- **Filmdetails**: Detaillierte Ansicht einzelner Filme
- **Dynamische Hintergrundbilder**: Zufällige Hintergrundbilder für eine ansprechende UI
- **API-Integration**: Abruf aktueller Filmdaten von The Movie Database (TMDB)

## 🏗️ Technische Architektur

Die App folgt dem **MVVM (Model-View-ViewModel)** Pattern und implementiert **Clean Architecture** Prinzipien:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   UI Layer      │    │  Domain Layer   │    │   Data Layer    │
│                 │    │                 │    │                 │
│ • Compose UI    │◄──►│ • ViewModels    │◄──►│ • Repository    │
│ • Screens       │    │ • Use Cases     │    │ • API Service   │
│ • Components    │    │ • Models        │    │ • DTOs          │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Architektur-Komponenten

#### 1. **UI Layer (Presentation)**
- **Jetpack Compose**: Moderne deklarative UI-Entwicklung
- **Navigation**: Type-safe Navigation zwischen Screens
- **State Management**: Reaktive UI mit StateFlow und Recomposition

#### 2. **Domain Layer (Business Logic)**
- **ViewModels**: State-Management und Business Logic
- **UI State**: Zentrale Verwaltung des App-Zustands
- **Use Cases**: Geschäftslogik-Operationen

#### 3. **Data Layer (Data Access)**
- **Repository Pattern**: Abstraktion der Datenquellen
- **API Integration**: REST API mit Retrofit
- **Dependency Injection**: Hilt für lose Kopplung

## 🛠️ Verwendete Technologien

### Core Android
- **Android SDK**: API Level 24+ (Android 7.0)
- **Kotlin**: Moderne Programmiersprache für Android
- **Jetpack Compose**: Deklarative UI-Toolkit
- **Material Design 3**: Moderne Design-Sprache

### Architektur & State Management
- **MVVM Pattern**: Model-View-ViewModel Architektur
- **ViewModel**: Lifecycle-aware Komponente für UI-Daten
- **StateFlow**: Reaktive Streams für State Management
- **Kotlin Coroutines**: Asynchrone Programmierung

### Navigation
- **Navigation Compose**: Type-safe Navigation
- **NavHost**: Navigation zwischen Screens
- **NavController**: Programmgesteuerte Navigation

### Networking & Data
- **Retrofit**: HTTP Client für API-Aufrufe
- **Moshi**: JSON Serialisierung/Deserialisierung
- **OkHttp**: HTTP Client mit Interceptors
- **Coil**: Bildladung für Compose

### Dependency Injection
- **Hilt**: Dependency Injection Framework
- **@Inject**: Constructor Injection
- **@Module**: Dependency Configuration
- **@Provides/@Binds**: Dependency Provision

## 📁 Projektstruktur

```
app/src/main/java/de/manuelwenner/moviejunkie/
├── di/                          # Dependency Injection
│   ├── AppModule.kt            # Repository Bindings
│   └── NetworkModule.kt        # Network Dependencies
├── data/                       # Data Layer
│   ├── network/               # API & Network
│   │   ├── MovieApiService.kt  # Retrofit Interface
│   │   ├── MovieDto.kt        # Data Transfer Objects
│   │   └── AuthInterceptor.kt  # API Authentication
│   └── repository/            # Repository Pattern
│       ├── IMovieRepository.kt # Repository Interface
│       └── MovieRepository.kt  # Repository Implementation
├── model/                     # Domain Models
│   └── Movie.kt              # Business Model
├── screens/                   # UI Screens
│   ├── MovieJunkieApp.kt     # Main Navigation
│   ├── HomeScreen.kt         # Film-Liste Screen
│   └── MovieDetailScreen.kt  # Film-Details Screen
├── ui/                       # UI Components
│   ├── components/           # Reusable Components
│   ├── theme/               # App Theme
│   └── viewmodels/          # ViewModels
│       └── MovieViewModel.kt # Main ViewModel
├── MainActivity.kt           # App Entry Point
└── MovieJunkieApplication.kt # Application Class
```

## 🔧 Dependency Injection mit Hilt

Die App verwendet **Hilt** für Dependency Injection, um eine lose Kopplung zwischen Komponenten zu erreichen:

### NetworkModule
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): MovieApiService
}
```

### RepositoryModule
```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(
        movieRepository: MovieRepository
    ): IMovieRepository
}
```

### Verwendung in ViewModels
```kotlin
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: IMovieRepository
) : ViewModel()
```

## 🌐 API Integration

Die App integriert mit **The Movie Database (TMDB)** API:

- **Base URL**: `https://api.themoviedb.org/3/`
- **Authentication**: Bearer Token über Interceptor
- **Endpoint**: `/discover/movie` für beliebte Filme
- **Data Format**: JSON mit Moshi Converter

### API Response Mapping
```kotlin
data class MovieDto(
    val title: String,
    @Json(name = "vote_average") val rating: Double,
    @Json(name = "poster_path") val imageUrl: String
)
```

## 🎨 UI/UX Design

### Design System
- **Material Design 3**: Moderne Design-Sprache
- **Responsive Layout**: Anpassung an verschiedene Bildschirmgrößen
- **Dark/Light Theme**: Unterstützung für verschiedene Themes
- **Accessibility**: Screen Reader Unterstützung

### Komponenten
- **LazyColumn**: Effiziente Liste für große Datenmengen
- **Scaffold**: Basis-Layout mit AppBar
- **TopAppBar**: Navigation und Titel
- **Card**: Film-Items mit Material Design

## 🚀 Setup & Installation

### Voraussetzungen
- **Android Studio**: Arctic Fox oder neuer
- **JDK**: Version 11 oder höher
- **Android SDK**: API Level 24+
- **Gradle**: 8.0+

### Installation
1. **Repository klonen**:
   ```bash
   git clone <repository-url>
   cd android-movie-junkie-app
   ```

2. **Dependencies installieren**:
   ```bash
   ./gradlew build
   ```

3. **App starten**:
   - Android Studio öffnen
   - Projekt importieren
   - Emulator oder Gerät verbinden
   - "Run" Button drücken

### API Key Konfiguration
Der API Key für TMDB ist bereits in der `NetworkModule.kt` konfiguriert. Für Produktionsumgebungen sollte dieser in eine separate Konfigurationsdatei ausgelagert werden.

## 📚 Lernziele

Diese App demonstriert folgende Android-Entwicklungskonzepte:

### Grundlagen
- ✅ **Activity Lifecycle**: MainActivity mit onCreate()
- ✅ **Android Manifest**: App-Konfiguration
- ✅ **Resources**: Strings, Drawables, Layouts

### UI Development
- ✅ **Jetpack Compose**: Deklarative UI-Entwicklung
- ✅ **State Management**: remember, Recomposition
- ✅ **Material Design**: Moderne UI-Komponenten
- ✅ **Navigation**: Screen-zu-Screen Navigation

### Architektur
- ✅ **MVVM Pattern**: Model-View-ViewModel
- ✅ **Clean Architecture**: Schichtentrennung
- ✅ **Repository Pattern**: Datenabstraktion
- ✅ **Dependency Injection**: Hilt Integration

### Reactive Programming
- ✅ **Kotlin Flow**: Reaktive Streams
- ✅ **StateFlow**: State Management
- ✅ **Coroutines**: Asynchrone Programmierung
- ✅ **ViewModel**: Lifecycle-aware Components

### Networking
- ✅ **Retrofit**: HTTP Client
- ✅ **REST API**: The Movie Database Integration
- ✅ **JSON Parsing**: Moshi Converter
- ✅ **Error Handling**: Exception Management

## 🔍 Code-Beispiele

### State Management mit StateFlow
```kotlin
class MovieViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> = _uiState.asStateFlow()
    
    fun loadMovies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // API Call...
        }
    }
}
```

### Compose UI mit State
```kotlin
@Composable
fun HomeScreen(viewModel: MovieViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    
    LazyColumn {
        items(uiState.movies) { movie ->
            MovieItem(movie) { /* Navigation */ }
        }
    }
}
```

### Repository Pattern
```kotlin
class MovieRepository @Inject constructor(
    private val apiService: MovieApiService
) : IMovieRepository {
    override suspend fun fetchPopularMovies(): List<MovieDto> {
        return apiService.getDiscoverMovie().results
    }
}
```

## 🧪 Testing

Die App enthält grundlegende Test-Strukturen:
- **Unit Tests**: ViewModel und Repository Tests
- **UI Tests**: Compose UI Tests
- **Integration Tests**: End-to-End Tests

### Test ausführen
```bash
./gradlew test          # Unit Tests
./gradlew connectedAndroidTest  # UI Tests
```

## 📱 Screenshots

Die App zeigt:
1. **Home Screen**: Liste beliebter Filme mit dynamischem Hintergrund
2. **Detail Screen**: Detaillierte Filminformationen
3. **Navigation**: Smooth Transitions zwischen Screens

## 🤝 Beitragen

Dieses Projekt dient als Lernressource für Android-Entwicklung. Verbesserungsvorschläge und Bug-Fixes sind willkommen:

1. Fork des Repositories
2. Feature Branch erstellen
3. Änderungen committen
4. Pull Request erstellen

## 📄 Lizenz

Dieses Projekt steht unter der MIT-Lizenz. Siehe `LICENSE` Datei für Details.

## 👨‍💻 Autor

**Manuel Wenner**
- Android Developer & Trainer
- Spezialisiert auf Jetpack Compose und moderne Android-Architekturen

---

*Diese App wurde als praktischer Teil einer Android Compose Schulung entwickelt und demonstriert moderne Android-Entwicklungspraktiken.*
