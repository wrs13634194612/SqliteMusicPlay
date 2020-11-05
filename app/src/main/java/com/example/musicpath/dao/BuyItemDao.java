package com.example.musicpath.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.musicpath.model.BuyItem;

import java.util.List;

@Dao
public interface BuyItemDao {

    @Insert
    void insertItemToDB(BuyItem buyItem);


    @Delete
    void removeItemFromDB(BuyItem buyItem);

    @Query("SELECT * FROM buy_item_table ORDER BY name DESC")
    LiveData<List<BuyItem>> getAllItemsFromDB();

}
