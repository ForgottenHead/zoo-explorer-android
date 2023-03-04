package com.mendelu.xstast12.zooexplorer.database.animals

import com.mendelu.xstast12.zooexplorer.models.Animal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class IAnimalLocalRepositoryImplTest: IAnimalLocalRepository {
    var mock: Animal = Animal(1,"name", "latin_name", "continent", "animalClass",
        "order", "family", "exposition", "length",
        "weight", "georange", "diet", "lifespan", "iucn", 49.232798,
        16.533356, listOf("obrazok"), "model3d", false )

    override fun getAll(): List<Animal> {
        return  listOf(mock)
    }

    override fun getAllFlow(): Flow<List<Animal>> {
        return flowOf(listOf(mock))
    }

    override fun getFavouritesFlow(fav: Boolean): Flow<List<Animal>> {
        return flowOf(listOf(mock))
    }

    override suspend fun findById(id: Long): Animal {
        return mock
    }

    override suspend fun insert(animal: Animal): Long {
        return 1
    }

    override suspend fun changeFavState(id: Long, state: Boolean) {

    }

    override suspend fun getFavoriteState(id: Long): Boolean {
        return true
    }
}