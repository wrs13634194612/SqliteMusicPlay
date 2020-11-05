package com.example.musicpath.util;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;


import com.example.musicpath.bean.MusicBean;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2020/8/2.
 */

public class MusicUtil {


    public List<MusicBean> getMusicData(Context context) {
        List<MusicBean> oList = new ArrayList<MusicBean>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                MusicBean music = new MusicBean();
                //歌曲ID
                long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                //歌曲名
                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                //演唱者
                String author = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                //路径
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                //时长
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                //专辑
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                //大小
                String size = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));

                if (author.equals("<unkonw>")) {
                    author = "未知艺术家";
                }

                if (duration > 3000) {
                    music.setName(name);
                    music.setAuthor(author);
                    music.setPath(path);
                    music.setDuration(duration);
                    music.setSize(size);
                    oList.add(music);
                }
            }
        }
        return oList;
    }

}
