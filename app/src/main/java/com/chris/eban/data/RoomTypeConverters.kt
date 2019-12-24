package com.chris.eban.data

import androidx.room.TypeConverter
import java.util.*


class RoomTypeConverters {


    @TypeConverter
    fun fromTimeStamp(timeStamp: Long?): Date? {
        return if (timeStamp == null) null else Date(timeStamp)
    }


    @TypeConverter
    fun dateToTimeStamp(date: Date): Long = date.time

    fun dateToTimeStamp(date: Date?): Long? {
        return date?.time
    }

}
