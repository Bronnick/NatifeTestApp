package com.natife.natifetestapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.natife.natifetestapp.ui.screens.GifListScreen
import com.natife.natifetestapp.view_models.AppViewModel

@Composable
fun NatifeTestApp(

) {
    val scope = rememberCoroutineScope()

    val appViewModel: AppViewModel =
        viewModel(factory = AppViewModel.Factory)


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

    ) {
        paddingValues ->
        paddingValues
        NatifeTestApp()
    }
}