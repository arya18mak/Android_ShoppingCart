package com.example.shoppingdemo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface DbDAO {
    @Insert
    void insertItem(DbTable... dbTables);

    @Query("Select * from DbTable")
    LiveData<List<DbTable>> getAllItems();

    @Query("Select COUNT(*) from DbTable")
    LiveData<Integer> countItems();

    @Delete
    void deleteItem(DbTable dbTable);
}
