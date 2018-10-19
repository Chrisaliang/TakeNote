package com.chris.takenote.data.pojo

import com.google.gson.annotations.SerializedName

/**
 * common http response
 */
data class CommonResult<T>(
        @SerializedName("error") val error: Boolean,
        @SerializedName("results") val results: T
)
