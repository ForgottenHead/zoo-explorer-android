package com.mendelu.xstast12.zooexplorer.di.test

import com.mendelu.xstast12.zooexplorer.ZooExplorerApplication
import com.mendelu.xstast12.zooexplorer.database.animals.AnimalDatabase
import org.koin.dsl.module

val databaseModuleTest = module {
    fun provideDatabase(): AnimalDatabase
            = AnimalDatabase.getDatabase(ZooExplorerApplication.appContext)

    single { provideDatabase() }
}