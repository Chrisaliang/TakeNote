package com.chris.eban.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chris.eban.data.Tables.EVENT.Companion.COLUMN_CONTENT
import com.chris.eban.data.Tables.EVENT.Companion.COLUMN_TITLE
import com.chris.eban.data.Tables.EVENT.Companion.TABLE_NAME
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME)
@Deprecated("see in DataEvent")
class Event {

    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    var id: Long = 0

    @SerializedName(COLUMN_TITLE)
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String? = null

    @SerializedName(COLUMN_CONTENT)
    @ColumnInfo(name = COLUMN_CONTENT)
    var content: String? = null

    override fun toString(): String {
        return "Event(id=$id, title=$title, content=$content)"
    }

}
