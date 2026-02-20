package com.natife.natifetestapp.ui.screens

import android.net.Uri
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.natife.natifetestapp.view_models.AppViewModel

private object Routes {
    const val LIST = "gif_list"
    const val FULL = "gif_full"
    const val URL_ARG = "url"
}

@OptIn(ExperimentalMaterial3Api::class)
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

    val appViewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)

    val navController = rememberNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        topBar = {
            if(currentRoute == Routes.LIST) {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 8.dp)
                        ) {
                            TextField(
                                value = appViewModel.requestText,
                                onValueChange = { appViewModel.setReqText(it) },
                                modifier = Modifier.weight(1f),
                                placeholder = { Text("Search GIFs") },
                                singleLine = true,
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            if (appViewModel.requestText.isNotBlank()) {
                                                appViewModel.getGifInfo(
                                                    appViewModel.requestText,
                                                    appViewModel.limit
                                                )
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Search,
                                            contentDescription = "Search"
                                        )
                                    }
                                }
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { appViewModel.setMenuExtend(true) }) {
                            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")
                        }

                        DropdownMenu(
                            expanded = appViewModel.isMenuExtended,
                            onDismissRequest = { appViewModel.setMenuExtend(false) }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {

                                        IconButton(
                                            onClick = {
                                                appViewModel.setNewLimit(
                                                    (appViewModel.limit - 1).coerceIn(5, 20))
                                            },
                                            enabled = appViewModel.limit > 5
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ChevronLeft,
                                                contentDescription = "Decrease"
                                            )
                                        }

                                        Text("Limit: ${appViewModel.limit}")

                                        IconButton(
                                            onClick = {
                                                appViewModel.setNewLimit(
                                                    (appViewModel.limit + 1).coerceIn(5, 20))
                                            },
                                            enabled = appViewModel.limit < 20
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ChevronRight,
                                                contentDescription = "Increase"
                                            )
                                        }
                                    }
                                },
                                onClick = { /* keep menu open */ },
                                enabled = false
                            )
                        }
                    }
                )
            }
        }

    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.LIST,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Routes.LIST) {
                GifListScreen(
                    gifUiState = appViewModel.gifUiState,
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


}