package com.mendelu.xstast12.zooexplorer.datastore

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first


class DataStoreRepositoryImpl(private val context: Context)
    :IDataStoreRepository {

    override suspend fun setDatabaseVersion(version: Double) {
        val settingsKey = doublePreferencesKey(DataStoreConstants.DB_VERSION)
        context.dataStore.edit { settings ->
            settings[settingsKey] = version
        }

    }

    override suspend fun getDatabaseVersion(): Double {
        return try {
            val settingsKey = doublePreferencesKey(DataStoreConstants.DB_VERSION)
            val settings = context.dataStore.data.first()
            if(!settings.contains(settingsKey)) {
                0.0
            }else{
                settings[settingsKey]!!
            }
        }catch (e: Exception){
            e.printStackTrace()
            0.0
        }
    }
}