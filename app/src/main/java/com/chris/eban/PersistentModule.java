package com.chris.eban;


import com.chris.eban.data.AppDatabase;
import com.chris.eban.data.EventDao;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

/**
 * 持久化模块
 */
@Module
class PersistentModule {

    private static final String DB_NAME = "eBan.db";

    @Provides
    @Singleton
    static AppDatabase getDatabase(App app) {
        return Room.databaseBuilder(app, AppDatabase.class, DB_NAME).build();
    }

    @Provides
    @Singleton
    static EventDao getEventDao(AppDatabase database) {
        return database.getEventDao();
    }
}
