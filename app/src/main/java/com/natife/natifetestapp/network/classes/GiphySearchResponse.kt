package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class GiphySearchResponse(
    @SerializedName("data") val data: List<GifObject> = emptyList(),
    @SerializedName("meta") val meta: Meta? = null,
    @SerializedName("pagination") val pagination: Pagination? = null
)