package com.mendelu.xstast12.zooexplorer.di

import com.mendelu.xstast12.zooexplorer.ZooExplorerApplication
import com.mendelu.xstast12.zooexplorer.database.animals.AnimalDatabase
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(): AnimalDatabase
    = AnimalDatabase.getDatabase(ZooExplorerApplication.appContext)

    single { provideDatabase() }
}