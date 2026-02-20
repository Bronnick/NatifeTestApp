package com.natife.natifetestapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage

@Composable
fun GifFullScreen(
    gifUrl: String,
    imageLoader: ImageLoader,
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = gifUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            imageLoader = imageLoader
        )
    }
}