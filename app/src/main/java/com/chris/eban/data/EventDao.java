package com.chris.eban.data;

import androidx.room.Dao;

@Dao
public abstract class EventDao {

    public abstract void getEventList();
}
