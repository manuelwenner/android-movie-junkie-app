package de.manuelwenner.moviejunkie.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.manuelwenner.moviejunkie.model.Movie

@Composable
fun MovieItem(movie: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        //horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "\uD83C\uDFAC ${movie.title}",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        AnimatedRatingStars(movie.rating)

    }
}

@Composable
fun AnimatedRatingStars(rating: Float) {
    var startAnimation by remember { mutableStateOf(false) }

    // Animiert die Bewertung von 0 auf den Zielwert
    val animatedRating by animateFloatAsState(
        targetValue = if (startAnimation) rating else 0f,
        animationSpec = tween(
            durationMillis = 2500,
            easing = FastOutSlowInEasing
        ),
        label = "starRatingAnimation"
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    // Wir haben maximal 10 Sterne
    val totalStars = 10
    val filledStars = animatedRating.toInt()
    val partialStar = animatedRating - filledStars

    Row(verticalAlignment = Alignment.CenterVertically) {
        // Ganze Sterne
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(20.dp)
            )
        }

        // Teilweise gefüllter Stern (wenn nötig)
        if (partialStar > 0f && filledStars < totalStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Yellow.copy(alpha = partialStar),
                modifier = Modifier.size(20.dp)
            )
        }

        // Leere Sterne
        repeat(totalStars - filledStars - if (partialStar > 0f) 1 else 0) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Gray.copy(alpha = 0.3f),
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = String.format(" %.1f / 10", animatedRating),
            fontSize = 14.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}
