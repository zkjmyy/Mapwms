//package com.gxsn.gaodemapdemo.utils;
//
//import com.amap.api.maps.model.UrlTileProvider;
//import com.gxsn.jianceyunliangzhu.model.PositionModel;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * Created by zkj on 2017/08/02
// * android-maps-utils-master
// */
//
//public class HeritageScopeTileProvider extends UrlTileProvider {
//
//
//    private String mRootUrl;
//    //默认瓦片大小
//    private static int titleSize = 256;//a=6378137±2（m）
//    //基本参数
//    private final double initialResolution= 156543.03392804062;//2*Math.PI*6378137/titleSize;
//    private final double originShift      = 20037508.342789244;//2*Math.PI*6378137/2.0; 周长的一半
////    private final double initialResolution = 2 * Math.PI * 6378139 / titleSize;//2*Math.PI*6378137/titleSize;
////    private final double originShift = 2 * Math.PI * 6378139 / 2.0;//2*Math.PI*6378137/2.0; 周长的一半
//
//    private final double HALF_PI = Math.PI / 2.0;
//    private final double RAD_PER_DEGREE = Math.PI / 180.0;
//    private final double HALF_RAD_PER_DEGREE = Math.PI / 360.0;
//    private final double METER_PER_DEGREE = originShift / 180.0;//一度多少米
//    private final double DEGREE_PER_METER = 180.0 / originShift;//一米多少度
//
//
//
////    http://geoserver.geo-compass.com/geoserver/wms?service=WMS&version=1.1.0&request=GetMap&layers=lzjcy&styles=&bbox=119.88343924142934,30.364612314956208,120.06379555641601,30.460587810815216&width=768&height=408&srs=EPSG:900913&format=application/openlayers#toggle
//
//    public HeritageScopeTileProvider() {
//        super(titleSize, titleSize);
////        mRootUrl = "LAYERS=cwh:protect_region_38_20160830&FORMAT=image%2Fpng&TRANSPARENT=TRUE&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&STYLES=&SRS=EPSG%3A900913&BBOX="
////        mRootUrl = "http://www.wochmoc.org.cn/geoserver/wms?LAYERS=cwh:protect_region_38_20160830&FORMAT=image%2Fpng&TRANSPARENT=TRUE&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&STYLES=&SRS=EPSG%3A900913&BBOX=";
//        mRootUrl = "http://geoserver.geo-compass.com/geoserver/wms?service=WMS&version=1.1.0&request=GetMap&layers=lzjcy&TRANSPARENT=TRUE&styles=&bbox=";
//    }
//
//
//    public HeritageScopeTileProvider(int i, int i1) {
//        super(i, i1);
//    }
//
//    @Override
//    public URL getTileUrl(int x, int y, int level) {
//
//        try {
//            String url = mRootUrl + TitleBounds(x, y, level);
////            Log.e("===",url);
//            return new URL(url);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//
//    /**
//     * 根据像素、等级算出坐标
//     *
//     * @param p
//     * @param zoom
//     * @return
//     */
//    private double Pixels2Meters(int p, int zoom) {
//        return p * Resolution(zoom) - originShift;
//    }
//
//    /**
//     * 根据瓦片的x/y等级返回瓦片范围
//     *
//     * @param tx
//     * @param ty
//     * @param zoom
//     * @return
//     */
//    private String TitleBounds(int tx, int ty, int zoom) {
//        double minX = Pixels2Meters(tx * titleSize, zoom);
//        double maxY = -Pixels2Meters(ty * titleSize, zoom);
//        double maxX = Pixels2Meters((tx + 1) * titleSize, zoom);
//        double minY = -Pixels2Meters((ty + 1) * titleSize, zoom);
//
//        //转换成经纬度
//        minX=Meters2Lon(minX);
//        minY=Meters2Lat(minY);
//        maxX=Meters2Lon(maxX);
//        maxY=Meters2Lat(maxY);
//        PositionModel position1 = PositionUtil.gcj_To_Gps84(minY,minX);
//        minX = position1.getWgLon();
//        minY = position1.getWgLat();
//        PositionModel position2 = PositionUtil.gcj_To_Gps84(maxY,maxX);
//        maxX = position2.getWgLon();
//        maxY = position2.getWgLat();
////        /**海龙屯测试打开*/
////        minX=Lon2Meter(minX);
////        minY=Lat2Meter(minY);
////        maxX=Lon2Meter(maxX);
////        maxY=Lat2Meter(maxY);
//
////        double testMinX = PositionUtil.gcj_To_Gps84(minY,minX).getWgLon();
////        CoordinateConverter converter  = new CoordinateConverter(MDCApplication.getAppContext());
////        converter.from(CoordinateConverter.CoordType.GPS);
////        converter.coord(new LatLng( minY,minX));
////        LatLng min=converter.convert();
////        converter.coord(new LatLng(maxY,maxX));
////        LatLng max=converter.convert();
////        double testMinX1 = -min.longitude+2*minX;
////        minX=Lon2Meter(-min.longitude+2*minX);
////        minY=Lat2Meter(-min.latitude+2*minY);
////        maxX=Lon2Meter(-max.longitude+2*maxX);
////        maxY=Lat2Meter(-max.latitude+2*maxY);
//        return minX + "," + Double.toString(minY) + "," + Double.toString(maxX) + "," + Double.toString(maxY) + "&width=256&height=256&srs=EPSG:900913&FORMAT=image%2Fpng";
//    }
//
//    /**
//     * 计算分辨率
//     *
//     * @param zoom
//     * @return
//     */
//    private double Resolution(int zoom) {
//        return initialResolution / (Math.pow(2, zoom));
//    }
//
//    /**
//     * X米转经纬度
//     */
//    private double Meters2Lon(double mx) {
//        double lon = mx * DEGREE_PER_METER;
//        return lon;
//    }
//
//    /**
//     * Y米转经纬度
//     */
//    private double Meters2Lat(double my) {
//        double lat = my * DEGREE_PER_METER;
//        lat = 180.0 / Math.PI * (2 * Math.atan(Math.exp(lat * RAD_PER_DEGREE)) - HALF_PI);
//        return lat;
//    }
//
//    /**
//     * X经纬度转米
//     */
//    private double Lon2Meter(double lon) {
//        double mx = lon * METER_PER_DEGREE;
//        return mx;
//    }
//
//    /**
//     * Y经纬度转米
//     */
//    private double Lat2Meter(double lat) {
//        double my = Math.log(Math.tan((90 + lat) * HALF_RAD_PER_DEGREE)) / (RAD_PER_DEGREE);
//        my = my * METER_PER_DEGREE;
//        return my;
//    }
//
//
//}
