package com.gxsn.gaodemapdemo.gaode.drawline;

import com.amap.api.maps.AMapUtils;

/**
 * Created by zkj on 2017/10/30
 * MapForWms
 */

public class LineUtils {



    /**
     * 使用三角形面积（使用海伦公式求得）相等方法计算点pX到点pA和pB所确定的直线的距离
     * @param start  起始经纬度
     * @param end    结束经纬度
     * @param center 前两个点之间的中心点
     * @return 中心点到 start和end所在直线的距离
     */
    public static double distToSegment(LatLngPoint start, LatLngPoint end, LatLngPoint center) {
        double a = Math.abs(AMapUtils.calculateLineDistance(start.latLng, end.latLng));
        double b = Math.abs(AMapUtils.calculateLineDistance(start.latLng, center.latLng));
        double c = Math.abs(AMapUtils.calculateLineDistance(end.latLng, center.latLng));
        double p = (a + b + c) / 2.0;
        double s = Math.sqrt(Math.abs(p * (p - a) * (p - b) * (p - c)));
        double d = s * 2.0 / a;
        return d;
    }



}
