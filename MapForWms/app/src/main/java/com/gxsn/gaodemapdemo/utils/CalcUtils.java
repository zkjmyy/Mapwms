package com.gxsn.gaodemapdemo.utils;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Description 运算相关类
 * Created by gpJiao on 2016/11/24.
 */

public class CalcUtils {
    /**
     * 判断字符串是否能转换成int
     *
     * @param str
     * @return
     */
    public static boolean isNumberic(String str) {
        if (TextUtils.isEmpty(str))
            return false;

        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 控制小数点后位数
     *
     * @param input
     * @param bt
     * @return
     */
    public static double decimalControl(double input, int bt) {
//        StringBuffer format = new StringBuffer("#.");
//        if(bt <= 0)
//                return input;
//        for (int i=1; i<=bt; i++)
//            format.append("#");
//
//        DecimalFormat df = new DecimalFormat(format.toString());
//        String reslut = df.format(input);
//        return Double.parseDouble(reslut);

        if (bt <= 0)
            return input;

        double result;
        try {
            BigDecimal b = new BigDecimal(input);
            result = b.setScale(bt, BigDecimal.ROUND_HALF_UP).doubleValue();
            return result;
        } catch (Exception e) {
            result = input;
            return result;
        }

    }
}
