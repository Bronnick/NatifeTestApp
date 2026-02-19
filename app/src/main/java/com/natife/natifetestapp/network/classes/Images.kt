package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("original") val original: MediaFormat? = null,
    @SerializedName("downsized") val downsized: MediaFormat? = null,
    @SerializedName("downsized_large") val downsizedLarge: MediaFormat? = null,
    @SerializedName("downsized_medium") val downsizedMedium: MediaFormat? = null,
    @SerializedName("downsized_small") val downsizedSmall: MediaFormat? = null,
    @SerializedName("downsized_still") val downsizedStill: MediaFormat? = null,

    @SerializedName("fixed_height") val fixedHeight: MediaFormat? = null,
    @SerializedName("fixed_height_downsampled") val fixedHeightDownsampled: MediaFormat? = null,
    @SerializedName("fixed_height_small") val fixedHeightSmall: MediaFormat? = null,
    @SerializedName("fixed_height_small_still") val fixedHeightSmallStill: MediaFormat? = null,
    @SerializedName("fixed_height_still") val fixedHeightStill: MediaFormat? = null,

    @SerializedName("fixed_width") val fixedWidth: MediaFormat? = null,
    @SerializedName("fixed_width_downsampled") val fixedWidthDownsampled: MediaFormat? = null,
    @SerializedName("fixed_width_small") val fixedWidthSmall: MediaFormat? = null,
    @SerializedName("fixed_width_small_still") val fixedWidthSmallStill: MediaFormat? = null,
    @SerializedName("fixed_width_still") val fixedWidthStill: MediaFormat? = null,

    @SerializedName("looping") val looping: MediaFormat? = null,
    @SerializedName("original_still") val originalStill: MediaFormat? = null,
    @SerializedName("original_mp4") val originalMp4: MediaFormat? = null,

    @SerializedName("preview") val preview: MediaFormat? = null,
    @SerializedName("preview_gif") val previewGif: MediaFormat? = null,
    @SerializedName("preview_webp") val previewWebp: MediaFormat? = null,

    @SerializedName("480w_still") val w480Still: MediaFormat? = null,

    @SerializedName("hd") val hd: MediaFormat? = null,
    @SerializedName("4k") val k4: MediaFormat? = null
)