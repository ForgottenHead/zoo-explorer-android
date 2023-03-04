package com.mendelu.xstast12.zooexplorer.communication

import com.mendelu.xstast12.zooexplorer.models.Animal
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface AnimalsAPI {

    @Headers("Content-Type: application/json")
    @GET("animals.json")
    suspend fun getAllAnimals(): Response<List<Animal>>

    @Headers("Content-Type: application/json")
    @GET("animals/{id}.json")
    suspend fun getAnimalById(@Path("id") id: Long): Response<Animal>

    @Headers("Content-Type: application/json")
    @GET("version.json")
    suspend fun getDatabaseVersion(): Response<Double>
}