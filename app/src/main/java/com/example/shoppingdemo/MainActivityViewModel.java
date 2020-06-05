package com.example.shoppingdemo;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainActivityViewModel extends AndroidViewModel {

    private ItemRepository itemRepository;
    private LiveData<List<DbTable>> items;
    private LiveData<Integer> itemCount;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application.getApplicationContext());
        items = itemRepository.getItems();
        itemCount = itemRepository.getItemCount();
    }

    public void insertNewItem(DbTable dbTable){
        itemRepository.addNewItem(dbTable);
    }

    public void deleteItem(DbTable dbTable){
        itemRepository.deleteItem(dbTable);
    }

    public LiveData<List<DbTable>> getItems()
    {
        return items;
    }

    public LiveData<Integer> getItemCount(){
        return itemCount;
    }
}
