package com.gxsn.gaodemapdemo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;

import com.gxsn.gaodemapdemo.MapApplication;

/**
 * Created by zkj on 2016/09/06
 * TheGreatWall
 */
public class UiUtils {


    public static Context getContext(){
        return MapApplication.mContext;
    }


    /** 获取资源 */
    public static Resources getResources() {
        System.out.println(".......haha" + getContext() == null ? true : false);
        return getContext().getResources();
    }


    /** 获取状态栏高度
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

}
