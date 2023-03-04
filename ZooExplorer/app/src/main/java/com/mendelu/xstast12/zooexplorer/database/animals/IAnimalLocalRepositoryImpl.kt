package com.mendelu.xstast12.zooexplorer.database.animals

import com.mendelu.xstast12.zooexplorer.models.Animal
import kotlinx.coroutines.flow.Flow

class IAnimalLocalRepositoryImpl(private val animalDao: AnimalDao)
    : IAnimalLocalRepository {
    override fun getAll(): List<Animal> {
        return animalDao.getAll()
    }

    override fun getAllFlow(): Flow<List<Animal>> {
        return animalDao.getAllFlow()
    }

    override fun getFavouritesFlow(fav: Boolean): Flow<List<Animal>> {
        return animalDao.getFavouritesFlow()
    }

    override suspend fun findById(id: Long): Animal {
        return animalDao.findById(id)
    }


    override suspend fun insert(animal: Animal): Long {
        return animalDao.insert(animal)
    }

    override suspend fun changeFavState(id: Long, state: Boolean) {
        animalDao.changeFavState(id, state)
    }

    override suspend fun getFavoriteState(id: Long): Boolean {
        return animalDao.getFavoriteState(id)
    }
}