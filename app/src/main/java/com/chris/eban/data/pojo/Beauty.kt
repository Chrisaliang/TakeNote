package com.chris.eban.data.pojo

import com.google.gson.annotations.SerializedName
import java.util.*

data class Beauty(
        @SerializedName("_id") var id: String?,
        @SerializedName("createdAt") var createTime: Date?,
        @SerializedName("desc") var desc: String?,
        @SerializedName("publishedAt") var publishTime: Date?,
        @SerializedName("source") var source: String?,
        @SerializedName("type") var type: String?,
        @SerializedName("url") var imgUrl: String?,
        @SerializedName("used") var used: Boolean,
        @SerializedName("who") var author: String?
)
