package com.mendelu.xstast12.zooexplorer.di

import android.content.Context
import com.mendelu.xstast12.zooexplorer.datastore.DataStoreRepositoryImpl
import com.mendelu.xstast12.zooexplorer.datastore.IDataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { provideDataStoreRepository(androidContext()) }
}

fun provideDataStoreRepository(context: Context): IDataStoreRepository
        = DataStoreRepositoryImpl(context)
