package com.natife.natifetestapp.ui.screens

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
import coil.compose.AsyncImage
import com.natife.natifetestapp.data.classes.GifInfo

val testArray = arrayOf(
    GifInfo(
        "e1", "https://media1.giphy.com/media/5nh1ZQPCHUXreebrd7/200w_d.gif"
    ),
    GifInfo(
        "e2", "https://media1.giphy.com/media/5nh1ZQPCHUXreebrd7/200w_d.gif"
    ),
    GifInfo(
        "e3", "https://media1.giphy.com/media/5nh1ZQPCHUXreebrd7/200w_d.gif"
    ),
    GifInfo(
        "e4", "https://media1.giphy.com/media/5nh1ZQPCHUXreebrd7/200w_d.gif"
    ),
    GifInfo(
        "e5", "https://media1.giphy.com/media/5nh1ZQPCHUXreebrd7/200w_d.gif"
    ),

)


@Composable
fun GifListScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        testArray.forEach { gifInfo ->
            GifInfoUi(gifInfo)
        }
    }
}

@Composable
fun GifInfoUi(
    gifInfo: GifInfo
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = gifInfo.id.toString(), textAlign = TextAlign.Center)
        AsyncImage(
            model = gifInfo.gifUrl,
            contentDescription = null,
            modifier = Modifier.size(160.dp),
            contentScale = ContentScale.Crop
        )
    }
}