package com.gxsn.gaodemapdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zkj on 2016/07/14
 * gulangyu
 */
public class AppVersionUtil {


    public static String getHandSetInfo(Activity activity, String record){

        String handSetInfo=
                null;
        try {
            handSetInfo = "错误码位置：" + record +",手机型号:" + android.os.Build.MODEL +
                    ",SDK版本:" + android.os.Build.VERSION.SDK +
                    ",系统版本:" + android.os.Build.VERSION.RELEASE+
                    ",软件版本:"+ getLocalVersion(activity) + "\n Userid: " + PreUtils.getString(activity,"userid","") + "\n 时间："
            + DateUtils.formatDateAndTime(System.currentTimeMillis()) + "\n 当前线程：" + Thread.currentThread().getName() + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handSetInfo;
    }


    /**
     * 获取本地版本号
     *
     * @return
     * @throws Exception
     */
    public static String getLocalVersion(Activity activity) throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = activity.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 获取本地软件版本
     */
    public static String getLocalVersion(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersionCode(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

}
