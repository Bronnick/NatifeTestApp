package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class Analytics(
    @SerializedName("onload") val onload: AnalyticsEvent? = null,
    @SerializedName("onclick") val onclick: AnalyticsEvent? = null,
    @SerializedName("onsent") val onsent: AnalyticsEvent? = null
)