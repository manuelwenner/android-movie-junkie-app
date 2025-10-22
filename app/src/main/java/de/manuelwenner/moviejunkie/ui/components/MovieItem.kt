package de.manuelwenner.moviejunkie.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.manuelwenner.moviejunkie.model.Movie
import de.manuelwenner.moviejunkie.ui.constants.UiConstants

@Composable
fun MovieItem(movie: Movie, onListItemClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UiConstants.Spacing.SM)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(UiConstants.CornerRadius.LG)
            )
            .padding(UiConstants.Spacing.MD)
            .clickable {
                onListItemClicked()
            },
        //horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "\uD83C\uDFAC ${movie.title}",
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = UiConstants.Typography.BODY_LARGE,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )

        Row(
            modifier = Modifier.padding(start = UiConstants.Spacing.SM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(movie.rating.toInt()) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.Yellow,
                    modifier = Modifier.size(UiConstants.Size.ICON_SM)
                )
            }
            Text(
                text = " (${movie.rating}/10)",
                fontSize = UiConstants.Typography.BODY_MEDIUM,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}