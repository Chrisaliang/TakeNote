package com.chris.eban

import androidx.room.Room
import com.chris.eban.data.AppDatabase
import com.chris.eban.data.EventDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 持久化模块
 */
@Module
internal object PersistentModule {

    private val DB_NAME = "eBan.db"

    @Provides
    @Singleton
    fun getDatabase(app: App): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, DB_NAME).build()
    }

    @Provides
    @Singleton
    fun getEventDao(database: AppDatabase): EventDao {
        return database.eventDao
    }
}
