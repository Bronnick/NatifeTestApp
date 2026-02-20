package com.natife.natifetestapp.ui.screens

import android.os.Build
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.natife.natifetestapp.data.classes.GifInfo
import coil.ImageLoader.Builder
import coil.util.DebugLogger
import coil.ComponentRegistry
import coil.decode.Decoder
import coil.request.ImageRequest
import coil.compose.AsyncImage

val testArray = arrayOf(
    GifInfo(
        "e1", "https://media1.giphy.com/media/5nh1ZQPCHUXreebrd7/200w_d.gif"
    ),
    GifInfo(
        "e2", "https://media4.giphy.com/media/EcB78c1B6ZrR09SP9c/200w_d.gif?cid=1244a2bc71s9wbjw5sivbm41ckyoowf9ptnr1ffrlanb1noy&ep=v1_gifs_search&rid=200w_d.gif&ct=g"
    ),
    GifInfo(
        "e3", "https://media1.giphy.com/media/oUyf6TtYULiCcGMBzB/200w.gif"
    ),
    GifInfo(
        "e4", "https://media4.giphy.com/media/EcB78c1B6ZrR09SP9c/200w_d.gif?cid=1244a2bc71s9wbjw5sivbm41ckyoowf9ptnr1ffrlanb1noy&ep=v1_gifs_search&rid=200w_d.gif&ct=g"
    ),
    GifInfo(
        "e5", "https://media2.giphy.com/media/DjMHR7zvxzoB5vMPa1/giphy.gif?cid=1244a2bc71s9wbjw5sivbm41ckyoowf9ptnr1ffrlanb1noy&ep=v1_gifs_search&rid=giphy.gif&ct=g"
    ),

)


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GifListScreen(
    onShowDetails: () -> Unit,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

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
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
                else add(GifDecoder.Factory())
            }
            .build()
    }
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
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader
        )
    }
}