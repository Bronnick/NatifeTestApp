package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class GifObject(
    @SerializedName("type") val type: String? = null,
    @SerializedName("id") val id: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("bitly_gif_url") val bitlyGifUrl: String? = null,
    @SerializedName("bitly_url") val bitlyUrl: String? = null,
    @SerializedName("embed_url") val embedUrl: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("source") val source: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("rating") val rating: String? = null,
    @SerializedName("content_url") val contentUrl: String? = null,
    @SerializedName("source_tld") val sourceTld: String? = null,
    @SerializedName("source_post_url") val sourcePostUrl: String? = null,
    @SerializedName("is_sticker") val isSticker: Int? = null,
    @SerializedName("import_datetime") val importDatetime: String? = null,
    @SerializedName("trending_datetime") val trendingDatetime: String? = null,
    @SerializedName("images") val images: Images? = null,
    @SerializedName("user") val user: GiphyUser? = null,
    @SerializedName("analytics_response_payload") val analyticsResponsePayload: String? = null,
    @SerializedName("analytics") val analytics: Analytics? = null,
    @SerializedName("alt_text") val altText: String? = null,
    @SerializedName("is_low_contrast") val isLowContrast: Boolean? = null
)