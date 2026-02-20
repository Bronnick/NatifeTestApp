package com.natife.natifetestapp.data.repositories

import com.natife.natifetestapp.BuildConfig
import com.natife.natifetestapp.data.classes.GifInfo
import com.natife.natifetestapp.network.services.GifService

class GifRepository(
    private val gifService: GifService
) {

    //private val apiKey="CkZ2NsWKne3eGsdWFuuzajomjhrIAetx"

    suspend fun getGifList (
        q: String,
        limit: Int
    ): List<GifInfo> {
        return gifService.getGiphySearchResponse( q, limit).data.map { gif ->
            GifInfo(
                    id = gif.id,
                    gifUrl = gif.images?.fixedWidth?.url ?: gif.images?.original?.url,
                )
        }
    }
}

