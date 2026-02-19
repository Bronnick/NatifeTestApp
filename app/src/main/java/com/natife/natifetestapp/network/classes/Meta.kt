package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("status") val status: Int? = null,
    @SerializedName("msg") val msg: String? = null,
    @SerializedName("response_id") val responseId: String? = null
)