package com.mnessim.rsstracker.domain.repositories

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.mnessim.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepo(private val database: Database) {
    private val queries = database.preferencesQueries

    fun getAllPrefs(): Map<String, String> {
        return queries
            .getAllPreferences()
            .executeAsList()
            .associate { it.key to it.value_ }
    }

    fun getPrefByKey(key: String): String? {
        val value = queries.getPreferenceByKey(key).executeAsOneOrNull()?.value_
        return value
    }

    fun insertPref(key: String, value: String) {
        if (getPrefByKey(key) == null)
            queries.insertPreference(key, value)
    }

    fun updatePref(key: String, value: String) {
        if (getPrefByKey(key) == null) {
            queries.insertPreference(key, value)
        } else {
            queries.updatePreference(value, key)
        }
    }

    fun deletePref(key: String) {
        queries.deletePreferenceByKey(key)
    }

    fun deleteAll() {
        queries.deleteAllPreferences()
    }

    fun getSnoozedFlow(): Flow<Boolean> {
        return queries.getPreferenceByKey("snoozed")
            .asFlow()
            .mapToOneOrNull(Dispatchers.IO)
            .map { it?.value_ == "true" }
    }
}