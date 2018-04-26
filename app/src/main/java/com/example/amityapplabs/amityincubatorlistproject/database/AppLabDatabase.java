package com.example.amityapplabs.amityincubatorlistproject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {AppLabUser.class}, version = 1)
public abstract class AppLabDatabase extends RoomDatabase {

    public abstract AppLabDAO appLabDAO();
}