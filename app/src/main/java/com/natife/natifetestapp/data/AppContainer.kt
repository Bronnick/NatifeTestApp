package com.natife.natifetestapp.data

import com.natife.natifetestapp.data.repositories.GifRepository
import com.natife.natifetestapp.network.services.GifService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.lazy

val appContainer by lazy{
    AppContainer()
}

class AppContainer {
    private val baseUrl = "api.giphy.com/v1/"



    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService by lazy{
        retrofit.create<GifService>()
    }

    val gifRepository by lazy{
        GifRepository(retrofitService)
    }
}