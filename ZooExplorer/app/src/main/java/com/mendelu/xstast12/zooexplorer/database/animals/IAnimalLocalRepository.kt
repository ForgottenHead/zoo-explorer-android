package com.mendelu.xstast12.zooexplorer.database.animals

import com.mendelu.xstast12.zooexplorer.models.Animal
import kotlinx.coroutines.flow.Flow

interface IAnimalLocalRepository {
    fun getAll(): List<Animal>
    fun getAllFlow(): Flow<List<Animal>>
    fun getFavouritesFlow(fav: Boolean = true): Flow<List<Animal>>
    suspend fun findById(id : Long): Animal
    suspend fun insert(animal: Animal): Long
    suspend fun changeFavState(id: Long, state: Boolean)
    suspend fun getFavoriteState(id:Long): Boolean
}