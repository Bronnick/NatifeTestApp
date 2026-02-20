package com.natife.natifetestapp.view_models

import android.app.Application
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
import com.natife.natifetestapp.data.AppContainer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.natife.natifetestapp.data.repositories.SettingsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import okio.IOException


sealed interface GifUiState{
    data class Success(
        val gifList: List<GifInfo>
    ) : GifUiState
    object Loading : GifUiState
    object Waiting : GifUiState
    data class Error(
        val messageId: Int
    ) : GifUiState
}

class AppViewModel(
    private val gifRepository: GifRepository,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {

    private var isLoading by mutableStateOf(false)
    private var gifInfoJob: Job? = null

    var gifUiState: GifUiState by mutableStateOf(GifUiState.Waiting)
        private set

    var requestText: String by mutableStateOf("")
        private set

    var limit: Int by mutableStateOf(10)
        private set

    var isMenuExtended: Boolean by mutableStateOf(false)
        private set



    init {
        viewModelScope.launch {
            combine(
                settingsRepository.limitFlow.distinctUntilChanged(),
                settingsRepository.queryFlow.distinctUntilChanged()
            ) { savedLimit, savedQuery -> savedLimit to savedQuery }
                .collectLatest { (savedLimit, savedQuery) ->
                    limit = savedLimit.coerceIn(5, 20)
                    requestText = savedQuery
                }
        }
    }

    fun getGifInfo (
        q: String,
        limit: Int
    ) {
        if(isLoading) return
        isLoading = true
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
            isLoading = false
        }
    }

    fun setReqText(s: String) {
        requestText = s
        viewModelScope.launch {
            settingsRepository.setQuery(s)
        }
    }

    fun setNewLimit(newValue: Int) {
        limit = newValue
        viewModelScope.launch {
            settingsRepository.setLimit(newValue)
        }
    }

    fun setMenuExtend(newValue: Boolean) {
        isMenuExtended = newValue
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                val container = AppContainer(app)

                AppViewModel(
                    gifRepository = container.gifRepository,
                    settingsRepository = container.settingsRepository
                )
            }
        }
    }
}