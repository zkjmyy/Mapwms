package com.gxsn.gaodemapdemo.utils;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;


/**
 * Created by zkj on 2016/07/20
 * gulangyu
 */
public class AnimUtils {

    /**
     * 获取一个缩放透明渐变的动画集
     * @return
     */
    public static AnimationSet getAlphaAndScale() {
        Animation scaleAnimation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        //设置动画时间

        //初始化
        Animation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        //设置动画时间
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setDuration(200);
        return set;
    }



    public static AnimationSet getTransation() {
        //初始化
        Animation translateAnimation = new TranslateAnimation(0.1f, 80.0f,0f,0f);

        //设置动画时间
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.setDuration(3000);
        return set;
    }


    public static AnimationSet getTransationleft() {
        //初始化
        Animation translateAnimation = new TranslateAnimation(0.1f, -80.0f,0f,0f);

        //设置动画时间
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.setDuration(3000);
        return set;
    }


    /**
     * 获取一个缩放透明渐变的动画集,退出的动画
     * @return
     */
    public static AnimationSet getAlphaAndScaleExit() {
        Animation scaleAnimation1 = new ScaleAnimation(1.0f, 0.1f, 1.0f, 0.1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        //设置动画时间
        Animation alphaAnimation1 = new AlphaAnimation(1.0f, 0.1f);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(scaleAnimation1);
        set.addAnimation(alphaAnimation1);
        set.setDuration(200);
        return set;
    }


//    /**
//     * activity启动和关闭的动画
//     * @param activity
//     */
//    public static void actcitvityAnim(Activity activity) {
//        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
//        if (version > 5) {
//            activity.overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
//        }
//    }
}
