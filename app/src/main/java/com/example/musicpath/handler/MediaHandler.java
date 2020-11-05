package com.example.musicpath.handler;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.musicpath.interfacetype.HandlerMessageData;

public class MediaHandler extends Handler {
    HandlerMessageData mHandlerMessageData;

    public MediaHandler(@NonNull Looper looper) {
        super(looper);
    }

    public HandlerMessageData getmHandlerMessageData() {
        return mHandlerMessageData;
    }

    public void setmHandlerMessageData(HandlerMessageData mHandlerMessageData) {
        this.mHandlerMessageData = mHandlerMessageData;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        Log.e("TAG", "MediaHandler_getMessageData:" + msg.what);
        getmHandlerMessageData().getMessageData(msg);
    }
}
