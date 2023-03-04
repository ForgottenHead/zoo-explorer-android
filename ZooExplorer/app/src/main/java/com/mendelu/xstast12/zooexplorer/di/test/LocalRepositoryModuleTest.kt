package com.mendelu.xstast12.zooexplorer.di.test

import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepositoryImplTest
import org.koin.dsl.module

val localRepositoryModuleTest = module {
    single { provideLocalRepositoryTest() }
}
fun provideLocalRepositoryTest(): IAnimalLocalRepository {
    return IAnimalLocalRepositoryImplTest()
}