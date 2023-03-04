package com.mendelu.xstast12.zooexplorer.di

import com.mendelu.xstast12.zooexplorer.database.animals.AnimalDao
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepositoryImpl
import org.koin.dsl.module

val localRepositoryModule = module {
    fun provideLocalRepository(dao: AnimalDao): IAnimalLocalRepository {
        return IAnimalLocalRepositoryImpl(dao)
    }

    single { provideLocalRepository(get()) }
}