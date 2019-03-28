package com.chris.eban.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.chris.eban.data.Tables.EVENT.COLUMN_CONTENT;
import static com.chris.eban.data.Tables.EVENT.COLUMN_TITLE;
import static com.chris.eban.data.Tables.EVENT.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
class Event {

    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    public long id;

    @SerializedName(COLUMN_TITLE)
    @ColumnInfo(name = COLUMN_TITLE)
    public String title;

    @SerializedName(COLUMN_CONTENT)
    @ColumnInfo(name = COLUMN_CONTENT)
    public String content;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
