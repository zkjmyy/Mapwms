package com.gxsn.gaodemapdemo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zkj on 2016/05/09
 * inspect
 */
public class DateTimePickDialogUtil implements DatePicker.OnDateChangedListener,
        TimePicker.OnTimeChangedListener {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String dateTime;
    private String initDateTime;
    private Activity activity;

    /**
     * 日期时间弹出选择框构造函数
     * @param activity
     *            ：调用的父activity
     * @param initDateTime
     */
    public DateTimePickDialogUtil(Activity activity, String initDateTime) {
        this.activity = activity;
        this.initDateTime = initDateTime;

    }

    public void init(DatePicker datePicker, TimePicker timePicker) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {

        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "-"
                    + calendar.get(Calendar.MONTH) + "-"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "- "
                    + calendar.get(Calendar.HOUR_OF_DAY) + ":"
                    + calendar.get(Calendar.MINUTE);
        }

        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
    }

//    /**
//     * 弹出日期时间选择框方法
//     *
//     * @param inputDate
//     *            :为需要设置的日期时间文本编辑框
//     * @return
//     */
//    public AlertDialog dateTimePicKDialog(final EditText inputDate) {
//        LinearLayout dateTimeLayout = (LinearLayout) activity
//                .getLayoutInflater().inflate(R.layout.common_datetime, null);
//        datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
//        timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
//        init(datePicker, timePicker);
//        timePicker.setIs24HourView(true);
//        timePicker.setOnTimeChangedListener(this);
//
//        ad = new AlertDialog.Builder(activity)
//                .setTitle(initDateTime)
//                .setView(dateTimeLayout)
//                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        inputDate.setText(dateTime);
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                    }
//                }).show();
//
//        onDateChanged(null, 0, 0, 0);
//        return ad;
//    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        onDateChanged(null, 0, 0, 0);
    }

    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();

        calendar.set(datePicker.getYear(), datePicker.getMonth(),
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        dateTime = sdf.format(calendar.getTime());
        ad.setTitle(dateTime);
    }

}
