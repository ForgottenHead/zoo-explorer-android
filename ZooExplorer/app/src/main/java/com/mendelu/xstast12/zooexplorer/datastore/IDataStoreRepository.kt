package com.mendelu.xstast12.zooexplorer.datastore

interface IDataStoreRepository {
    suspend fun setDatabaseVersion(version: Double)
    suspend fun getDatabaseVersion():Double
}