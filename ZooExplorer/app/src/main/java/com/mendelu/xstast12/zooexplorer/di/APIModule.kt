package com.mendelu.xstast12.zooexplorer.di

import com.mendelu.xstast12.zooexplorer.communication.AnimalsAPI
import org.koin.dsl.module
import retrofit2.Retrofit

val  apiModule = module {
    single { providePetsApi(get()) }
}

fun providePetsApi(retrofit: Retrofit): AnimalsAPI
        = retrofit.create(AnimalsAPI::class.java)

