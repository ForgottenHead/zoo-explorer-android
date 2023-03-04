package com.mendelu.xstast12.zooexplorer.communication

import com.mendelu.xstast12.zooexplorer.architecture.CommunicationResult
import com.mendelu.xstast12.zooexplorer.models.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class AnimalsRemoteRepositoryImpl(private val animalsAPI: AnimalsAPI)
    :IAnimalsRemoteRepository {
    override suspend fun getAllAnimals(): CommunicationResult<List<Animal>> {
        return try {
            processResponse(withContext(Dispatchers.IO){animalsAPI.getAllAnimals()})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }

    override suspend fun getAnimalById(id: Long): CommunicationResult<Animal> {
        return try {
            processResponse(withContext(Dispatchers.IO){animalsAPI.getAnimalById(id)})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }
    }

    override suspend fun getDatabaseVersion(): CommunicationResult<Double> {
        return try {
            processResponse(withContext(Dispatchers.IO){animalsAPI.getDatabaseVersion()})

        }catch (timeoutException: SocketTimeoutException){
            return CommunicationResult.Exception(timeoutException)

        }catch (unknownHostEx: UnknownHostException){
            return CommunicationResult.Exception(unknownHostEx)
        }      }
}