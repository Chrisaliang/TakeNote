package com.chris.eban.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
abstract class AppDatabase extends RoomDatabase {

}