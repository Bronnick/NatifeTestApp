package com.natife.natifetestapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.appDataStore by preferencesDataStore(name = "app_prefs")

object PrefKeys {
    val LIMIT = intPreferencesKey("limit")
    val QUERY = stringPreferencesKey("query")
}