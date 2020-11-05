package com.example.musicpath.dao;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.musicpath.model.BuyItem;


@Database(entities = {BuyItem.class}, version = 1)
public abstract class BuyItemDB extends RoomDatabase {


    public static BuyItemDB instance;

    public abstract BuyItemDao getBuyItemDao();

    public static synchronized BuyItemDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BuyItemDB.class,
                    "buy_items_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
