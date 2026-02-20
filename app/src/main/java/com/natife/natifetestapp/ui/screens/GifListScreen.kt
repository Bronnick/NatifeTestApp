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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.natife.natifetestapp.data.classes.GifInfo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.stringResource
import com.natife.natifetestapp.view_models.GifUiState


/*val testArray = arrayOf(
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

)*/



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
    modifier: Modifier,
    gifInfo: GifInfo,
    imageLoader: ImageLoader
) {
    val context = LocalContext.current
    /*val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
                else add(GifDecoder.Factory())
            }
            .build()
    }*/
    Row(
        modifier = Modifier.fillMaxWidth().then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = gifInfo.id.toString(), textAlign = TextAlign.Center)
        AsyncImage(
            model = gifInfo.gifUrl,
            contentDescription = null,
            modifier = Modifier
                .size(160.dp),
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader
        )
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
        gifList.forEach { gifInfo ->
            GifInfoUi(
                modifier = Modifier.clickable {
                    val encoded = Uri.encode(gifInfo.gifUrl)
                    onClick(encoded ?: "null")
                },
                gifInfo = gifInfo,
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