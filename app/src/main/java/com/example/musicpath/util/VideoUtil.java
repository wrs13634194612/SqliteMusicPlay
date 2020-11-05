package com.example.musicpath.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.musicpath.bean.VideoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/8/3.
 */

public class VideoUtil {

    private static final String[] sLocalVideoColumns = {
            MediaStore.Video.Media._ID, // 视频id
            MediaStore.Video.Media.DATA, // 视频路径
            MediaStore.Video.Media.SIZE, // 视频字节大小
            MediaStore.Video.Media.DISPLAY_NAME, // 视频名称 xxx.mp4
            MediaStore.Video.Media.TITLE, // 视频标题
            MediaStore.Video.Media.DATE_ADDED, // 视频添加到MediaProvider的时间
            MediaStore.Video.Media.DATE_MODIFIED, // 上次修改时间，该列用于内部MediaScanner扫描，外部不要修改
            MediaStore.Video.Media.MIME_TYPE, // 视频类型 video/mp4
            MediaStore.Video.Media.DURATION, // 视频时长
            MediaStore.Video.Media.ARTIST, // 艺人名称
            MediaStore.Video.Media.ALBUM, // 艺人专辑名称
            MediaStore.Video.Media.RESOLUTION, // 视频分辨率 X x Y格式
            MediaStore.Video.Media.DESCRIPTION, // 视频描述
            MediaStore.Video.Media.IS_PRIVATE,
            MediaStore.Video.Media.TAGS,
            MediaStore.Video.Media.CATEGORY, // YouTube类别
            MediaStore.Video.Media.LANGUAGE, // 视频使用语言
            MediaStore.Video.Media.LATITUDE, // 拍下该视频时的纬度
            MediaStore.Video.Media.LONGITUDE, // 拍下该视频时的经度
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.MINI_THUMB_MAGIC,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BOOKMARK // 上次视频播放的位置
    };
    private static final String[] sLocalVideoThumbnailColumns = {
            MediaStore.Video.Thumbnails.DATA, // 视频缩略图路径
            MediaStore.Video.Thumbnails.VIDEO_ID, // 视频id
            MediaStore.Video.Thumbnails.KIND,
            MediaStore.Video.Thumbnails.WIDTH, // 视频缩略图宽度
            MediaStore.Video.Thumbnails.HEIGHT // 视频缩略图高度
    };


    public List<VideoBean> GetMvData(Context context) {
        List<VideoBean> mList = new ArrayList<VideoBean>();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, sLocalVideoColumns,
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                VideoBean videoInfo = new VideoBean();

                int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE));
                String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                long dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DATE_ADDED));
                long dateModified = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DATE_MODIFIED));
                String mimeType = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.MIME_TYPE));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.ARTIST));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.ALBUM));
                String resolution = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.RESOLUTION));
                String description = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DESCRIPTION));
                int isPrivate = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.IS_PRIVATE));
                String tags = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TAGS));
                String category = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.CATEGORY));
                double latitude = cursor.getDouble(cursor.getColumnIndex(MediaStore.Video.Media.LATITUDE));
                double longitude = cursor.getDouble(cursor.getColumnIndex(MediaStore.Video.Media.LONGITUDE));
                int dateTaken = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.DATE_TAKEN));
                int miniThumbMagic = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.MINI_THUMB_MAGIC));
                String bucketId = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID));
                String bucketDisplayName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                int bookmark = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media.BOOKMARK));

                ContentResolver thumbresolver = context.getContentResolver();
                Cursor thumbnailCursor = thumbresolver.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, sLocalVideoThumbnailColumns,
                        MediaStore.Video.Thumbnails.VIDEO_ID + "=" + id, null, null);
                if (thumbnailCursor != null && thumbnailCursor.moveToFirst()) {
                    do {
                        String thumbnailData = thumbnailCursor.getString(thumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
                        int kind = thumbnailCursor.getInt(thumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.KIND));
                        long width = thumbnailCursor.getLong(thumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.WIDTH));
                        long height = thumbnailCursor.getLong(thumbnailCursor.getColumnIndex(MediaStore.Video.Thumbnails.HEIGHT));


                        videoInfo.setThumbnailData(thumbnailData);
                        videoInfo.setKind(kind);
                        videoInfo.setWidth(width);
                        videoInfo.setHeight(height);
                    } while (thumbnailCursor.moveToNext());

                    thumbnailCursor.close();
                }

                videoInfo.setId(id);
                videoInfo.setData(data);
                videoInfo.setSize(size);
                videoInfo.setDisplayName(displayName);
                videoInfo.setTitle(title);
                videoInfo.setDateAdded(dateAdded);
                videoInfo.setDateModified(dateModified);
                videoInfo.setMimeType(mimeType);
                videoInfo.setDuration(duration);
                videoInfo.setArtist(artist);
                videoInfo.setAlbum(album);
                videoInfo.setResolution(resolution);
                videoInfo.setDescription(description);
                videoInfo.setIsPrivate(isPrivate);
                videoInfo.setTags(tags);
                videoInfo.setCategory(category);
                videoInfo.setLatitude(latitude);
                videoInfo.setLongitude(longitude);
                videoInfo.setDateTaken(dateTaken);
                videoInfo.setMiniThumbMagic(miniThumbMagic);
                videoInfo.setBucketId(bucketId);
                videoInfo.setBucketDisplayName(bucketDisplayName);
                videoInfo.setBookmark(bookmark);


                mList.add(videoInfo);
            } while (cursor.moveToNext());

            cursor.close();
        }
        return mList;
    }

}

