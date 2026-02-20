package com.natife.natifetestapp.ui.screens

import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.natife.natifetestapp.data.classes.GifInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.natife.natifetestapp.view_models.GifUiState

@Composable
fun GifListScreen(
    modifier: Modifier = Modifier,
    gifUiState: GifUiState,
    onClick: (String) -> Unit,
    imageLoader: ImageLoader
) {

    Box(
        contentAlignment = Alignment.Center
    ) {
        when (gifUiState) {
            is GifUiState.Success -> SuccessScreen (
                gifList = gifUiState.gifList,
                imageLoader = imageLoader,
                onClick = onClick
            )
            is GifUiState.Waiting -> {
                WaitingScreen()
            }
            is GifUiState.Error -> ErrorScreen(
                messageId = gifUiState.messageId
            )
            is GifUiState.Loading -> LoadingScreen()
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GifInfoUi(
    modifier: Modifier = Modifier,
    gifInfo: GifInfo,
    index: Int,
    imageLoader: ImageLoader
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AsyncImage(
                model = gifInfo.gifUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                imageLoader = imageLoader
            )

            Text(
                text = "GIF $index",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun SuccessScreen(
    gifList: List<GifInfo>,
    imageLoader: ImageLoader,
    onClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        gifList.forEachIndexed { index, gifInfo ->
            GifInfoUi(
                modifier = Modifier.clickable {
                    val encoded = Uri.encode(gifInfo.gifUrl)
                    onClick(encoded ?: "null")
                },
                gifInfo = gifInfo,
                index = index + 1,
                imageLoader = imageLoader
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .sizeIn(
                    minHeight = 100.dp,
                    minWidth = 100.dp
                ),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun WaitingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Find the perfect GIF in seconds.",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ErrorScreen(
    messageId: Int
){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = stringResource(id = messageId),
            textAlign = TextAlign.Center,
        )
    }
}