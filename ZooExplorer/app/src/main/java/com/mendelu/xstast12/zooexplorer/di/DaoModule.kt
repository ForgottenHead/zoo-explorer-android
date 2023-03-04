package com.mendelu.xstast12.zooexplorer.di

import com.mendelu.xstast12.zooexplorer.database.animals.AnimalDao
import com.mendelu.xstast12.zooexplorer.database.animals.AnimalDatabase
import org.koin.dsl.module

val daoModule = module {
    fun provideDao(database: AnimalDatabase): AnimalDao = database.AnimalDao()

    single { provideDao(get()) }
}