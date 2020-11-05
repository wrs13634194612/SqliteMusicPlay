package com.example.musicpath.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.musicpath.adapter.BuyListAdapter;
import com.example.musicpath.dao.BuyItemsRespository;
import com.example.musicpath.model.BuyItem;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private BuyListAdapter mAdapter;
    private BuyItemsRespository repository;
    private LiveData<List<BuyItem>> allDataItems;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = BuyItemsRespository.getInstance(application);
        allDataItems = repository.getAllItemsFromRepo();
    }

    public LiveData<List<BuyItem>> getAllDataItemsFromViewModel() {
        return allDataItems;
    }

    public void insertItemDB(BuyItem buyItem) {
        repository.insertItem(buyItem);
    }

    public void removeItemFromDB(BuyItem buyItem) {
        repository.removeItem(buyItem);
    }

}
