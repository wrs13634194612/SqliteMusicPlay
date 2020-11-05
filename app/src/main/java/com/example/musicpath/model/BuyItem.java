package com.example.musicpath.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buy_item_table")
public class BuyItem {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "amount")
    private String mAmount;

    @ColumnInfo(name = "path")
    private String mPath;

    @ColumnInfo(name = "channel")
    private String mChannel;

    @ColumnInfo(name = "time")
    private String mTimestamp;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String mAmount) {
        this.mAmount = mAmount;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String mPath) {
        this.mPath = mPath;
    }

    public String getChannel() {
        return mChannel;
    }

    public void setChannel(String mChannel) {
        this.mChannel = mChannel;
    }

    public String getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(String mTimestamp) {
        this.mTimestamp = mTimestamp;
    }
}
