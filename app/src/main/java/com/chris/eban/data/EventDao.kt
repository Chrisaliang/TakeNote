package com.chris.eban.data

import androidx.room.*

@Dao
abstract class EventDao {

    /***************  查询   ***************************/

    @Query("SELECT * FROM " + Tables.EVENT.TABLE_NAME + " ORDER BY id DESC")
    abstract fun queryEventList(): List<DataEvent>

    @Query("SELECT * FROM " + Tables.EVENT.TABLE_NAME + " WHERE id = :itemId ")
    abstract fun queryEventItem(itemId: Long): DataEvent

    /****************  插入  **************************/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEvent(event: DataEvent): Long

    /****************  更新   *************************/
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateEvent(event: DataEvent)

    /****************  删除   *************************/
    @Delete
    abstract fun deleteEvent(event: DataEvent)

    abstract fun insertEvent(event: Event): Long

    /****************  更新   *************************/
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateEvent(event: Event)

    /****************  删除   *************************/
    @Delete
    abstract fun deleteEvent(event: Event)

}
