package com.example.musicpath.dao;


import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.musicpath.model.BuyItem;

import java.util.List;

public class BuyItemsRespository {

    private BuyItemDao buyItemDao;
    private LiveData<List<BuyItem>> listFromDB;
    private static BuyItemsRespository mInstance = null;


    public BuyItemsRespository(Application application) {
        BuyItemDB database = BuyItemDB.getInstance(application);
        buyItemDao = database.getBuyItemDao();
        listFromDB = buyItemDao.getAllItemsFromDB();
    }

    public static BuyItemsRespository getInstance(Application application) {
        if (mInstance == null) {
            mInstance = new BuyItemsRespository(application);
        }
        return mInstance;
    }

    public LiveData<List<BuyItem>> getAllItemsFromRepo() {
        return listFromDB;
    }

    public void insertItem(BuyItem buyItem) {
        new InsertBuyItemAsyncTask(buyItemDao).execute(buyItem);
    }

    public void removeItem(BuyItem buyItem) {
        new RemoveBuyItemAsyncTask(buyItemDao).execute(buyItem);
    }

    private static class InsertBuyItemAsyncTask extends AsyncTask<BuyItem, Void, Void> {
        private BuyItemDao buyItemDao;

        public InsertBuyItemAsyncTask(BuyItemDao buyItemDao) {
            this.buyItemDao = buyItemDao;
        }

        @Override
        protected Void doInBackground(BuyItem... buyItems) {
            buyItemDao.insertItemToDB(buyItems[0]);
            return null;
        }
    }

    private static class RemoveBuyItemAsyncTask extends AsyncTask<BuyItem, Void, Void> {
        private BuyItemDao buyItemDao;

        public RemoveBuyItemAsyncTask(BuyItemDao buyItemDao) {
            this.buyItemDao = buyItemDao;
        }

        @Override
        protected Void doInBackground(BuyItem... buyItems) {
            buyItemDao.removeItemFromDB(buyItems[0]);
            return null;
        }
    }

}
