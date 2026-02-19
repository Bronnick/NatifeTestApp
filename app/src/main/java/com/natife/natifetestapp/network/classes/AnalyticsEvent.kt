package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class AnalyticsEvent(
    @SerializedName("url") val url: String? = null
)