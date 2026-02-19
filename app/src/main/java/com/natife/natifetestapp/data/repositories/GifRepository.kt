package com.natife.natifetestapp.data.repositories

import com.natife.natifetestapp.data.classes.GifInfo
import com.natife.natifetestapp.network.GifService

class GifRepository(
    private val gifService: GifService
) {

    private val apiKey="CkZ2NsWKne3eGsdWFuuzajomjhrIAetx"

    suspend fun getWeatherInfo (
        q: String,
        limit: Int
    ): List<GifInfo> {
        return gifService.getGiphySearchResponse(apiKey, q, limit).data.map { gif ->
            GifInfo(
                id = gif.id,
                gifUrl = gif.url,
                )
        }
    }
}

