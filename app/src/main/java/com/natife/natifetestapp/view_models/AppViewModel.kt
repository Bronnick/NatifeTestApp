package com.natife.natifetestapp.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natife.natifetestapp.data.classes.GifInfo
import com.natife.natifetestapp.data.repositories.GifRepository
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.natife.natifetestapp.R
import com.natife.natifetestapp.data.appContainer
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okio.IOException


sealed interface GifUiState{
    data class Success(
        val gifList: List<GifInfo>
    ) : GifUiState
    object Loading : GifUiState
    data class Error(
        val messageId: Int
    ) : GifUiState
}

class AppViewModel(
    private val gifRepository: GifRepository
) : ViewModel() {

    private var gifInfoJob: Job? = null

    var gifUiState: GifUiState by mutableStateOf(GifUiState.Loading)
        private set

    var requestText: String by mutableStateOf("")
        private set

    var limit: Int by mutableStateOf(10)

    var isMenuExtended: Boolean by mutableStateOf(false)
        private set



    init {
        getGifInfo("cats", 10)
    }

    fun getGifInfo (
        q: String,
        limit: Int
    ) {

        gifUiState = GifUiState.Loading
        gifInfoJob = viewModelScope.launch {
            do {
                gifUiState =
                    try {
                        GifUiState.Success(
                            gifRepository.getGifList(q, limit)
                        )
                    } catch (e: IOException) {
                        GifUiState.Error(
                            messageId = R.string.err_msg
                        )
                    }
            } while (gifUiState is GifUiState.Error)
        }
    }

    fun setReqText(s: String) {
        requestText = s
    }

    fun setMenuExtend(newValue: Boolean) {
        isMenuExtended = newValue
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val gifRepository = appContainer.gifRepository
                AppViewModel(gifRepository)
            }
        }
    }
}