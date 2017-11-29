package com.gxsn.gaodemapdemo.gaode.drawline;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zkj on 2017/10/30
 * MapForWms
 */

public class Douglas {


    private double dMax;
    private int start;
    private int end;
    private ArrayList<LatLngPoint> mLineInit;
    private ArrayList<LatLngPoint> mLineFilter = new ArrayList<>();



    public Douglas(ArrayList<LatLng> mLineInit, double dmax) {
        if (mLineInit == null) {
            throw new IllegalArgumentException("传入的经纬度坐标list == null");
        }
        this.dMax = dmax;
        this.start = 0;
        this.end = mLineInit.size() - 1;
        for (int i = 0; i < mLineInit.size(); i++) {
            this.mLineInit.add(new LatLngPoint(i, mLineInit.get(i)));
        }

    }

    /**
     * 压缩经纬度点
     *
     * @return
     */
    public ArrayList<LatLng> compress() {
        int size = mLineInit.size();
        ArrayList<LatLngPoint> latLngPoints = compressLine(mLineInit.toArray(new LatLngPoint[size]), mLineFilter, start, end, dMax);
        latLngPoints.add(mLineInit.get(0));
        latLngPoints.add(mLineInit.get(size-1));
        //对抽稀之后的点进行排序
        Collections.sort(latLngPoints, new Comparator<LatLngPoint>() {
            @Override
            public int compare(LatLngPoint o1, LatLngPoint o2) {
                return o1.compareTo(o2);
            }
        });
        ArrayList<LatLng> latLngs = new ArrayList<>();
        for (LatLngPoint point : latLngPoints) {
            latLngs.add(point.latLng);
        }
        return latLngs;
    }


    /**
     * 根据最大距离限制，采用DP方法递归的对原始轨迹进行采样，得到压缩后的轨迹
     * x
     *
     * @param originalLatLngs 原始经纬度坐标点数组
     * @param endLatLngs      保持过滤后的点坐标数组
     * @param start           起始下标
     * @param end             结束下标
     * @param dMax            预先指定好的最大距离误差
     */
    private ArrayList<LatLngPoint> compressLine(LatLngPoint[] originalLatLngs, ArrayList<LatLngPoint> endLatLngs, int start, int end, double dMax) {
        if (start < end) {
            //递归进行调教筛选
            double maxDist = 0;
            int currentIndex = 0;
            for (int i = start + 1; i < end; i++) {
                double currentDist = LineUtils.distToSegment(originalLatLngs[start], originalLatLngs[end], originalLatLngs[i]);
                if (currentDist > maxDist) {
                    maxDist = currentDist;
                    currentIndex = i;
                }
            }
            //若当前最大距离大于最大距离误差
            if (maxDist >= dMax) {
                //将当前点加入到过滤数组中
                endLatLngs.add(originalLatLngs[currentIndex]);
                //将原来的线段以当前点为中心拆成两段，分别进行递归处理
                compressLine(originalLatLngs, endLatLngs, start, currentIndex, dMax);
                compressLine(originalLatLngs, endLatLngs, currentIndex, end, dMax);
            }
        }
        return endLatLngs;
    }


}
