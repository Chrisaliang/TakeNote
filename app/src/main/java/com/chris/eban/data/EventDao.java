package com.chris.eban.data;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public abstract class EventDao {

    /*************** 查询 ***************/
    @Query("select * from " + Tables.EVENT.TABLE_NAME)
    public abstract List<Event> queryEventList();


    /****************  插入 ***************/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long insertEvent(Event event);
}
