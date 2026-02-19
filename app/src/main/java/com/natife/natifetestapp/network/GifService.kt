package com.natife.natifetestapp.network


import com.natife.natifetestapp.network.classes.GiphySearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface GifService {

    @GET("gifs/search")
    suspend fun getGiphySearchResponse(
        @Header("api_key") apiKey: String,
        @Query("q") request: String,
        @Query("limit") limit: Int = 10
    ) : GiphySearchResponse {
        return GiphySearchResponse()
    }
}
