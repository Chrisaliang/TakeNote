package com.chris.eban.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chris.eban.data.Tables.EVENT.Companion.COLUMN_CONTENT
import com.chris.eban.data.Tables.EVENT.Companion.COLUMN_CREATE_TIME
import com.chris.eban.data.Tables.EVENT.Companion.COLUMN_TITLE
import com.chris.eban.data.Tables.EVENT.Companion.COLUMN_UPDATE_TIME
import com.chris.eban.data.Tables.EVENT.Companion.TABLE_NAME
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = TABLE_NAME)
data class DataEvent(
        @PrimaryKey(autoGenerate = true) @Expose(serialize = false, deserialize = false)
        @ColumnInfo(name = "id")
        var id: Long,
        @SerializedName(COLUMN_TITLE) @ColumnInfo(name = COLUMN_TITLE)
        var title: String,
        @SerializedName(COLUMN_CONTENT) @ColumnInfo(name = COLUMN_CONTENT)
        var content: String,
        @SerializedName(COLUMN_CREATE_TIME) @ColumnInfo(name = COLUMN_CREATE_TIME)
        var createTime: Date,
        @SerializedName(COLUMN_UPDATE_TIME) @ColumnInfo(name = COLUMN_UPDATE_TIME)
        var updateTime: Date
) {
    override fun toString(): String {
        return "DataEvent(id=$id, title=$title, content=$content, createTime=$createTime," +
                " updateTime=$updateTime)"
    }
}
