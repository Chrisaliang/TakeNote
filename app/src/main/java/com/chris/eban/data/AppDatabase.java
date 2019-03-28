package com.chris.eban.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(
        entities = {
                Event.class
        }, version = 1, exportSchema = false)
@TypeConverters({RoomTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract EventDao getEventDao();
}
