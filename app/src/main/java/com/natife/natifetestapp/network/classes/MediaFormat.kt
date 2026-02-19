package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class MediaFormat(
    @SerializedName("height") val height: String? = null,
    @SerializedName("width") val width: String? = null,
    @SerializedName("size") val size: String? = null,

    @SerializedName("url") val url: String? = null,

    @SerializedName("mp4_size") val mp4Size: String? = null,
    @SerializedName("mp4") val mp4: String? = null,

    @SerializedName("webp_size") val webpSize: String? = null,
    @SerializedName("webp") val webp: String? = null,

    @SerializedName("frames") val frames: String? = null,
    @SerializedName("hash") val hash: String? = null
)