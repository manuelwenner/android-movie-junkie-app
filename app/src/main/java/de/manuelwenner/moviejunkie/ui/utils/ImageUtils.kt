package de.manuelwenner.moviejunkie.ui.utils

import android.content.Context
import coil.request.ImageRequest
import de.manuelwenner.moviejunkie.R

/**
 * Image Loading Utilities für konsistente Bildverarbeitung
 */
object ImageUtils {
    
    private const val TMDB_BASE_URL = "https://image.tmdb.org/t/p/"
    
    /**
     * Erstellt einen ImageRequest für TMDB Bilder
     * @param context Der Android Context
     * @param imageUrl Der relative Bildpfad von TMDB
     * @param size Die Bildgröße (w500, w780, original, etc.)
     * @return Konfigurierter ImageRequest
     */
    fun createTmdbImageRequest(
        context: Context,
        imageUrl: String,
        size: String = "w500"
    ): ImageRequest {
        return ImageRequest.Builder(context)
            .data("$TMDB_BASE_URL$size$imageUrl")
            .crossfade(true)
            .placeholder(R.drawable.movie)
            .error(R.drawable.movie)
            .build()
    }
    
    /**
     * Erstellt einen ImageRequest für TMDB Bilder mit Standard-Konfiguration
     * @param context Der Android Context
     * @param imageUrl Der relative Bildpfad von TMDB
     * @return Konfigurierter ImageRequest mit w500 Größe
     */
    fun createTmdbImageRequest(context: Context, imageUrl: String): ImageRequest {
        return createTmdbImageRequest(context, imageUrl, "w500")
    }
}
