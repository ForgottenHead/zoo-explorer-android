package com.mendelu.xstast12.zooexplorer.di.test

import android.content.Context
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepositoryImpl
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepositoryImplTEST
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val internalStorageModuleTest = module{
    single { provideInternalStorageRepositoryTest() }
}

fun provideInternalStorageRepositoryTest():
        InternalStorageRepository = InternalStorageRepositoryImplTEST()