package com.gxsn.gaodemapdemo.utils;

import android.os.CountDownTimer;
import android.os.Handler;

/**
 * Created by zkj on 2017/08/15
 * GreatWall
 */

public class RegisterCodeTimerUtils extends CountDownTimer {
    private static Handler mHandler;
    public static final int IN_RUNNING = 1001;
    public static int END_RUNNING = 1002;

    /**
     * @param millisInFuture
     *            // 倒计时的时长
     * @param countDownInterval
     *            // 间隔时间
     * @param handler
     *            // 通知进度的Handler
     */
    public RegisterCodeTimerUtils(long millisInFuture, long countDownInterval,
                                  Handler handler) {
        super(millisInFuture, countDownInterval);
        mHandler = handler;
    }

    // 结束
    @Override
    public void onFinish() {
        // TODO Auto-generated method stub
        if (mHandler != null)
            mHandler.obtainMessage(END_RUNNING, "重发").sendToTarget();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // TODO Auto-generated method stub
        if (mHandler != null)
            mHandler.obtainMessage(IN_RUNNING,
                    (millisUntilFinished / 1000) + "s 后重发").sendToTarget();
    }

}