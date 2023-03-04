package com.mendelu.xstast12.zooexplorer.database.animals

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mendelu.xstast12.zooexplorer.models.Animal
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    @Query("SELECT * FROM animal")
    fun getAll(): List<Animal>

    @Query("SELECT * FROM animal")
    fun getAllFlow(): Flow<List<Animal>>

    @Query("SELECT * FROM animal WHERE isFavourite = :fav")
    fun getFavouritesFlow(fav: Boolean = true): Flow<List<Animal>>

    @Query("SELECT * FROM animal WHERE id = :id")
    suspend fun findById(id : Long): Animal

    @Insert
    suspend fun insert(animal: Animal): Long

    @Query("UPDATE animal SET isFavourite = :state WHERE id = :id")
    suspend fun changeFavState(id: Long, state: Boolean)

    @Query("SELECT isFavourite FROM animal WHERE id = :id")
    suspend fun getFavoriteState(id:Long): Boolean
}