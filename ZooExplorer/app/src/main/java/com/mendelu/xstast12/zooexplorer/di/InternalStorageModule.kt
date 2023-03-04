package com.mendelu.xstast12.zooexplorer.di

import android.content.Context
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val internalStorageModule = module{
    single { provideInternalStorageRepository(androidContext()) }
}

fun provideInternalStorageRepository(context: Context):
        InternalStorageRepository = InternalStorageRepositoryImpl(context)