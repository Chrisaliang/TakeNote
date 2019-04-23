package com.chris.eban.data

import androidx.room.*

@Dao
abstract class EventDao {

    /***************  查询   ***************************/
    @Query("select * from " + Tables.EVENT.TABLE_NAME + " order by id desc")
    abstract fun queryEventList(): List<Event>


    /****************  插入  **************************/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEvent(event: Event): Long

    /****************  更新   *************************/
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateEvent(event: Event)

    /****************  删除   *************************/
    @Delete
    abstract fun deleteEvent(event: Event)

}
