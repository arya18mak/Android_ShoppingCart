package com.example.shoppingdemo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = DbTable.class , version = 1)
public abstract class DbDatabase extends RoomDatabase {
    public abstract DbDAO dbDao();



    private static DbDatabase dbDatabaseInstance;

    public static synchronized DbDatabase getInstance(Context context)
    {
        if(dbDatabaseInstance==null)
        {
            dbDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),DbDatabase.class,"ShopDb").build();
        }

        return dbDatabaseInstance;
    }


}
