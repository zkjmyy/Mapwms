package com.gxsn.gaodemapdemo.utils;

/**
 * Created by zkj on 2016/12/27
 * GreatWall
 */

public class LocationManagerUtil {


//    public static void locationManager(Activity activity) {
//        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
//
//        // 判断GPS是否正常启动
//        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            Toast.makeText(activity, "请开启GPS导航...", Toast.LENGTH_SHORT).show();
//            // 返回开启GPS导航设置界面
////            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
////            activity.startActivityForResult(intent, 0);
//            return;
//        }
//
//        // 为获取地理位置信息时设置查询条件
//        String bestProvider = locationManager.getBestProvider(getCriteria(), true);
//        // 获取位置信息
//        // 如果不设置查询要求，getLastKnownLocation方法传人的参数为LocationManager.GPS_PROVIDER
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
////        updateView(location);
//        // 监听状态
////        locationManager.addGpsStatusListener(listener);
//        // 绑定监听，有4个参数
//        // 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种
//        // 参数2，位置信息更新周期，单位毫秒
//        // 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
//        // 参数4，监听
//        // 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
//
//        // 1秒更新一次，或最小位移变化超过1米更新一次；
//        // 注意：此处更新准确度非常低，推荐在service里面启动一个Thread，在run中sleep(10000);然后执行handler.sendMessage(),更新位置
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
//    }
//
////    private static Location mLocation;
//
//    private static final LocationListener locationListener = new LocationListener() {
//        public static final String TAG = "locationListener";
//
//        public void onLocationChanged(Location location) {
//            //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
////          PositionModel p = PositionUtil.gcj_To_Gps84(lat, lon);
//            TraceModel trace = new TraceModel();
//            trace.setId(UUID.randomUUID().toString());
//            trace.setUserid(CommonUtil.getCommonSP("userID"));
//            trace.setStatus(0);
//            trace.setLat(location.getLatitude());
//            trace.setLon(location.getLongitude());
//            trace.setTime(DateUtils.formatDateAndTimes(System.currentTimeMillis()));
//
//            PreUtils.setString(UiUtils.getContext(), "lat", String.valueOf(location.getLatitude()));
//            PreUtils.setString(UiUtils.getContext(), "lon", String.valueOf(location.getLongitude()));
//
//            TraceSqlManager.insert(trace);
//
////            ToastUtils.showToast("lat==" + location.getLatitude() + "lon==" + location.getLongitude());
//        }
//
//        public void onProviderDisabled(String provider) {
//            //Provider被disable时触发此函数，比如GPS被关闭
//            Log.d(TAG, "LocationListener  onProviderDisabled");
//        }
//
//        public void onProviderEnabled(String provider) {
//            // Provider被enable时触发此函数，比如GPS被打开
//            Log.d(TAG, "LocationListener  onProviderEnabled");
//        }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Log.d(TAG, "LocationListener  onStatusChanged");
//            // Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
//            if (status == LocationProvider.OUT_OF_SERVICE || status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
//            }
//        }
//    };
//
//
//    private static Criteria getCriteria() {
//        Criteria criteria = new Criteria();
//        // 设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        // 设置是否要求速度
//        criteria.setSpeedRequired(false);
//        // 设置是否允许运营商收费
//        criteria.setCostAllowed(false);
//        // 设置是否需要方位信息
//        criteria.setBearingRequired(false);
//        // 设置是否需要海拔信息
//        criteria.setAltitudeRequired(false);
//        // 设置对电源的需求
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//        return criteria;
//    }

}
