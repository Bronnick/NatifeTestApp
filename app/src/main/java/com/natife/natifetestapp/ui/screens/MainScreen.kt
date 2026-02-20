package com.natife.natifetestapp.ui.screens

import android.net.Uri
import android.os.Build
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

private object Routes {
    const val LIST = "gif_list"
    const val FULL = "gif_full"
    const val URL_ARG = "url"
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory())
                else add(GifDecoder.Factory())
            }
            .build()
    }
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LIST,
        modifier = Modifier
    ) {
        composable(Routes.LIST) {
            GifListScreen(
                onClick = { index ->
                    navController.navigate("${Routes.FULL}/$index")
                },
                imageLoader = imageLoader
            )
        }

        composable(
            route = "${Routes.FULL}/{${Routes.URL_ARG}}",
            arguments = listOf(navArgument(Routes.URL_ARG) { type = NavType.StringType })
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString(Routes.URL_ARG).orEmpty()
            val gifUrl = Uri.decode(encodedUrl)

            GifFullScreen(
                gifUrl = gifUrl,
                imageLoader = imageLoader
            )
        }
    }


}