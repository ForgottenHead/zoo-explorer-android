package com.mendelu.xstast12.zooexplorer.di

import com.mendelu.xstast12.zooexplorer.communication.AnimalsAPI
import com.mendelu.xstast12.zooexplorer.communication.AnimalsRemoteRepositoryImpl
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single { provideAnimalsRemoteRepository(get()) }
}

fun provideAnimalsRemoteRepository(animalsAPI: AnimalsAPI): AnimalsRemoteRepositoryImpl
        = AnimalsRemoteRepositoryImpl(animalsAPI)

