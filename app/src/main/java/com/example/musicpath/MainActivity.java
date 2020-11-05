package com.example.musicpath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.musicpath.adapter.BuyListAdapter;
import com.example.musicpath.bean.MusicBean;
import com.example.musicpath.bean.VideoBean;
import com.example.musicpath.handler.MediaHandler;
import com.example.musicpath.interfacetype.HandlerMessageData;
import com.example.musicpath.model.BuyItem;
import com.example.musicpath.util.MusicUtil;
import com.example.musicpath.util.VideoUtil;
import com.example.musicpath.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements HandlerMessageData {
    //video
    private VideoUtil videoUtil;
    private List<VideoBean> mvList;
    //music
    private List<MusicBean> musicList;
    private MusicBean music;
    private MusicUtil musicUtil;
    //上下文
    private Context mContext;
    private MediaHandler mediaHandler;
    //sqlite
    private MainViewModel mainViewModel;
    private BuyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        //handler message
        mediaHandler = new MediaHandler(Looper.myLooper());
        mediaHandler.setmHandlerMessageData(this);
        //动态权限申请
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            Log.e("TAG", "statr thread:");
            getLocalMusicData();
        }

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getAllDataItemsFromViewModel().observe(this,
                new Observer<List<BuyItem>>() {
                    @Override
                    public void onChanged(List<BuyItem> buyItems) {
                        //设置数据源
                        adapter.updateAdapter(buyItems);
                    }
                });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BuyListAdapter(this);
        recyclerView.setAdapter(adapter);

        //delete item sqlite
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mainViewModel.removeItemFromDB(adapter.getCurrentBuyItem(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);
    }

    /**
     * 获取本地存储的音乐和视频
     */
    private void getLocalMusicData() {
        new Thread(runnable).start();
    }

    protected Runnable runnable = new Runnable() {
        @Override
        public void run() {
            musicUtil = new MusicUtil();
            musicList = musicUtil.getMusicData(mContext);
            videoUtil = new VideoUtil();
            mvList = videoUtil.GetMvData(mContext);
            Log.e("TAG", "runnable:" + mvList.size());
            //异步消息处理机制 message
            mediaHandler.obtainMessage(103).sendToTarget();
        }
    };


    @Override
    public void getMessageData(Message msg) {
        Log.e("TAG", "handleMessage_getMessageData:" + msg.what + "==" + mvList.size());
        if (msg.what == 103) {
            //这个地方需要判断两个字符串是否相等 然后才能添加数据库
            for (int count = 0; count < mvList.size(); count++) {
                BuyItem buyItem = new BuyItem();
                buyItem.setName("序号" + count);
                buyItem.setAmount("时段" + count);
                buyItem.setPath("视频源" + mvList.get(count).getData());
                buyItem.setChannel("节目通道" + count);
                buyItem.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000).toString());
                mainViewModel.insertItemDB(buyItem);
            }
        }
    }
}