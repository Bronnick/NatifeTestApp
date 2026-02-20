package com.natife.natifetestapp.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.natife.natifetestapp.data.datastore.PrefKeys
import com.natife.natifetestapp.data.datastore.appDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsRepository(private val context: Context) {

    val limitFlow: Flow<Int> = context.appDataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs -> prefs[PrefKeys.LIMIT] ?: 10 }

    suspend fun setLimit(value: Int) {
        context.appDataStore.edit { prefs ->
            prefs[PrefKeys.LIMIT] = value
        }
    }

    val queryFlow: Flow<String> = context.appDataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs ->
            prefs[PrefKeys.QUERY] ?: ""
        }

    suspend fun setQuery(value: String) {
        context.appDataStore.edit { prefs ->
            prefs[PrefKeys.QUERY] = value
        }
    }
}