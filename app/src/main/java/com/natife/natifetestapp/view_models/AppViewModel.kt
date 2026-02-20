package com.natife.natifetestapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.natife.natifetestapp.data.classes.GifInfo
import com.natife.natifetestapp.data.repositories.GifRepository
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.natife.natifetestapp.data.appContainer


sealed interface GifUiState{
    data class Success(
        val gifInfo: GifInfo
    ) : GifUiState
    object Loading : GifUiState
    data class Error(
        val messageId: Int
    ) : GifUiState
}

class AppViewModel(
    private val gifRepository: GifRepository
) : ViewModel() {
    private val gifUiState : GifUiState by mutableStateOf(GifUiState.Loading)


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val gifRepository = appContainer.gifRepository
                AppViewModel(gifRepository)
            }
        }
    }
}