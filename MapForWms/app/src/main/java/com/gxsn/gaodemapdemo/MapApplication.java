package com.gxsn.gaodemapdemo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by zkj on 2017/08/11
 * GaoDeMapDemo
 */

public class MapApplication extends Application {


    /**
     * 本地存储全路径名目录
     */
    public static String CACHE_DIR;



    public static final String CACHE_DIR_NAME = "GaoDeMap";

    /**
     * 离线地图储存目录
     */
    public static final String CACHE_OFFLINE_MAP_PATH;



    public static Context mContext;



    static {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            CACHE_DIR = Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + "/" + CACHE_DIR_NAME;
        } else {
            CACHE_DIR = Environment.getRootDirectory().getAbsolutePath() + "/"
                    + CACHE_DIR_NAME;
        }

        CACHE_OFFLINE_MAP_PATH = CACHE_DIR + "/map";
    }



    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;


        createCacheDir();
    }



    /**
     * 创建本地路径
     */
    private void createCacheDir() {
        File cacheDir = new File(CACHE_DIR);
        if (!cacheDir.exists())
            cacheDir.mkdirs();

    }

}
