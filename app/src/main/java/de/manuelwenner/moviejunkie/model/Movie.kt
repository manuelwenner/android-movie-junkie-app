package de.manuelwenner.moviejunkie.model

import de.manuelwenner.moviejunkie.R

data class Movie(val title: String, val rating: Float, val image: Int = R.drawable.default_item, val imageUrl: String)
