package com.mendelu.xstast12.zooexplorer.communication

import com.mendelu.xstast12.zooexplorer.architecture.CommunicationResult
import com.mendelu.xstast12.zooexplorer.architecture.IBaseRemoteRepository
import com.mendelu.xstast12.zooexplorer.models.Animal
import retrofit2.Response

interface IAnimalsRemoteRepository: IBaseRemoteRepository {
    suspend fun getAllAnimals(): CommunicationResult<List<Animal>>
    suspend fun getAnimalById(id: Long): CommunicationResult<Animal>
    suspend fun getDatabaseVersion(): CommunicationResult<Double>
}