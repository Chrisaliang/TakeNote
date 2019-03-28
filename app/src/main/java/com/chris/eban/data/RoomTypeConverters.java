package com.chris.eban.data;

import java.util.Date;

import androidx.room.TypeConverter;

public class RoomTypeConverters {

    @TypeConverter
    public static Date fromTimeStamp(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    public static Long dateToTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
