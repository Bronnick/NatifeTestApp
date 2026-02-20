package com.natife.natifetestapp.data

import com.natife.natifetestapp.BuildConfig
import com.natife.natifetestapp.data.repositories.GifRepository
import com.natife.natifetestapp.network.interceptors.ApiKeyInterceptor
import com.natife.natifetestapp.network.services.GifService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.lazy

val appContainer by lazy{
    AppContainer()
}

class AppContainer {
    private val baseUrl = "https://api.giphy.com/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(ApiKeyInterceptor(BuildConfig.GIPHY_API_KEY))
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService by lazy{
        retrofit.create<GifService>()
    }

    val gifRepository by lazy{
        GifRepository(retrofitService)
    }
}