package com.chris.eban.data

import androidx.room.TypeConverter
import java.util.*

object RoomTypeConverters {

    @TypeConverter
    fun fromTimeStamp(timeStamp: Long?): Date? {
        return if (timeStamp == null) null else Date(timeStamp)
    }

    fun dateToTimeStamp(date: Date?): Long? {
        return date?.time
    }
}
