package com.natife.natifetestapp.network.classes

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("total_count") val totalCount: Int? = null,
    @SerializedName("count") val count: Int? = null,
    @SerializedName("offset") val offset: Int? = null
)