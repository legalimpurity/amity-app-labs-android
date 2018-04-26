package com.example.amityapplabs.amityincubatorlistproject.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AppLabDAO {

    @Query("SELECT * FROM applabuser")
    List<AppLabUser> getAllUsers();

    @Insert
    void insert(AppLabUser user);

    @Delete
    void delete(AppLabUser user);
}