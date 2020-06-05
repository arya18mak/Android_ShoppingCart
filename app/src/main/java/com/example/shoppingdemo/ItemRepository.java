package com.example.shoppingdemo;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ItemRepository {

    private Context context;

    public ItemRepository(Context context)
    {
        this.context = context.getApplicationContext();
    }

    public void addNewItem(DbTable dbTable)
    {
        AsyncTask.execute(() -> DbDatabase.getInstance(context).dbDao().insertItem(dbTable));
    }

    public LiveData<List<DbTable>> getItems()
    {
        return DbDatabase.getInstance(context).dbDao().getAllItems();
    }

    public LiveData<Integer> getItemCount()
    {
        return DbDatabase.getInstance(context).dbDao().countItems();
    }

    public void deleteItem(DbTable dbTable)
    {
        AsyncTask.execute(() -> DbDatabase.getInstance(context).dbDao().deleteItem(dbTable));
    }

}
