package com.chris.eban.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
class Event {
    @PrimaryKey
    int id;
    String title;
}
