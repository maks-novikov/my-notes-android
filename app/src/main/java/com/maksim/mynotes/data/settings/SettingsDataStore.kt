package com.maksim.mynotes.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class SettingsDataStore(private val context: Context) {

    private val nameKey = stringPreferencesKey("name_key")
    suspend fun putName(name: String){
        context.settingsDataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }
}

private val SETTINGS_PREFERENCES_NAME = "settings_preferences"

private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(
    name = SETTINGS_PREFERENCES_NAME
)