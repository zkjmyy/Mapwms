package com.gxsn.gaodemapdemo.gaode.wmts;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import android.util.LruCache;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gxsn.gaodemapdemo.gaode.PositionModel;
import com.gxsn.gaodemapdemo.utils.PositionUtil;
import com.gxsn.gaodemapdemo.utils.UiUtils;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by gxsn on 2017/9/21.
 */

public class WmtsTileProvider {
    private LruCache<String, WmtsTile> mTitlePathCache;
    private HashMap<String, WmtsTile> mTileMap;
//    private ExecutorService mExecutorService = new De

    public WmtsTileProvider() {
        mTitlePathCache = new LruCache<>(90);
        mTileMap = new HashMap<>();
    }




    public void upateTiles(LatLngBounds mapBounds, float zoom) {
        mTileMap.clear();
        LatLngBounds bounds = mapBounds;
//        LatLng northEast = new LatLng(bounds.northeast.latitude, bounds.northeast.longitude);
//        LatLng southWest = new LatLng(bounds.southwest.latitude, bounds.southwest.longitude);

        PositionModel nePosition =  PositionUtil.gcj_To_Gps84(bounds.northeast.latitude, bounds.northeast.longitude);
        PositionModel swPosition =  PositionUtil.gcj_To_Gps84(bounds.southwest.latitude, bounds.southwest.longitude);

        Point ne = deg2num(nePosition.getWgLat(), nePosition.getWgLon(), zoom);
        Point sw = deg2num(swPosition.getWgLat(), swPosition.getWgLon(), zoom);

        int minX = sw.x-1;
        int minY = ne.y-1;
        int maxX = ne.x + 1;
        int maxY = sw.y + 1;
        int intZoom = (int) zoom-1;
        int numberLength = (((int)zoom)/6+1)*2;
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMinimumIntegerDigits(numberLength);
        int powZoom = (int) Math.pow(2,(int)zoom);
        for (int col = minX; col < maxX; col++) {
            for (int row = minY; row < maxY; row++) {
                final String key = intZoom + "_" + col + "_" + row;
                WmtsTile tile = mTitlePathCache.get(key);
                if (tile == null) {
                    tile = new WmtsTile();
                    LatLng nwTile = num2deg(col, row, zoom);
                    LatLng seTile = num2deg(col + 1, row + 1, zoom);
                    LatLng swTile = new LatLng(seTile.latitude, nwTile.longitude);
                    LatLng neTile = new LatLng(nwTile.latitude, seTile.longitude);
                    PositionModel swP = PositionUtil.gps84_To_Gcj02(swTile.latitude, swTile.longitude);
                    PositionModel neP = PositionUtil.gps84_To_Gcj02(neTile.latitude, neTile.longitude);
                    swTile = new LatLng(swP.getWgLat(), swP.getWgLon());
                    neTile = new LatLng(neP.getWgLat(), neP.getWgLon());
                    int numberRow = powZoom-row-1;

                    String url =  "http://172.16.100.74:9090/geoserver/gwc/service/wmts?layer=greatwall%3Agw_9_w&style=&tilematrixset=EPSG%3A900913&Service=WMTS&Request=GetTile&Version=1.0.0&Format=image%2Fpng&TileMatrix=EPSG%3A900913%3A"+ (int)zoom + "&TileCol=" + col + "&TileRow=" + row;
//                   String url =  "http://imageserver.wochmoc.org.cn/seis/pub/wmts/3/60?service=WMTS&request=GetTile&version=1.0.0&layer=ALL&style=&tilematrixSet=WGS84&format=image%2Fpng&transparent=true&height=256&width=256&opacity=1&zIndex=1&rgb=undefined&srs=EPSG%3A4326&tilematrix=" + (int)zoom + "&tilerow=" + row + "&tilecol=" + col;
//                   String url =  "http://219.234.147.220:18122/wmts?layer=yichandi_dt_hb&style=default&tilematrixset=WGS84&Service=WMTS&Request=GetTile&Version=1.0.0&Format=tiles&TileMatrix=" +(int)zoom + "&TileCol=" + col + "&TileRow=" +row;

//                    http://t6.tianditu.com/DataServer?T=cva_w&x=13494&y=6209&l=14

                    Log.e("resource", "resource: " + url );

//                    http://t6.tianditu.com/DataServer?T=cva_w&x=13494&y=6209&l=14
                    final WmtsTile finalTile = tile;
                    final LatLng finalSwTile = swTile;
                    final LatLng finalNeTile = neTile;
                    Glide.with(UiUtils.getContext()).load(url)
                            .asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                            Log.e("resource", "resource: " + url );
                            if (resource != null){
                                finalTile.bounds = new LatLngBounds(finalSwTile, finalNeTile);
                                finalTile.bitmap = resource;
                                mTitlePathCache.put(key, finalTile);
                                mTileMap.put(key, finalTile);
                            }
                        }
                    });

                }else{
                    mTileMap.put(key,tile);
                }

            }
        }
    }


//    public void upateTiles(LatLngBounds mapBounds, float zoom) {
//        mTileMap.clear();
//        LatLngBounds bounds = mapBounds;
////        LatLng northEast = new LatLng(bounds.northeast.latitude, bounds.northeast.longitude);
////        LatLng southWest = new LatLng(bounds.southwest.latitude, bounds.southwest.longitude);
//
//        PositionModel nePosition =  PositionUtil.gcj_To_Gps84(bounds.northeast.latitude, bounds.northeast.longitude);
//        PositionModel swPosition =  PositionUtil.gcj_To_Gps84(bounds.southwest.latitude, bounds.southwest.longitude);
//
//        //4326加载瓦片计算
//
//        double tileSpan3 = 360/Math.pow(2,(int)zoom);
//
//        int minX = (int) ((swPosition.getWgLon() - (-180))/tileSpan3);
//        int minY = (int) ((90 - nePosition.getWgLat())/tileSpan3);
//        int maxX = (int) ((nePosition.getWgLon() - (-180))/tileSpan3) + 1;
//        int maxY = (int) ((90 - swPosition.getWgLat())/tileSpan3) + 1;
//        int intZoom = (int) zoom;
//        int powZoom = (int) Math.pow(2,(int)zoom);
//
//
//        for (int col = minX; col < maxX; col++) {
//            for (int row = minY; row < maxY; row++) {
//                final String key = intZoom + "_" + col + "_" + row;
//                WmtsTile tile = mTitlePathCache.get(key);
//                if (tile == null) {
//                    tile = new WmtsTile();
//                    LatLng nwTile = num2deg(col, row, zoom);
//                    LatLng seTile = num2deg(col + 1, row + 1, zoom);
//                    LatLng swTile = new LatLng(seTile.latitude, nwTile.longitude);
//                    LatLng neTile = new LatLng(nwTile.latitude, seTile.longitude);
//                    PositionModel swP = PositionUtil.gps84_To_Gcj02(swTile.latitude, swTile.longitude);
//                    PositionModel neP = PositionUtil.gps84_To_Gcj02(neTile.latitude, neTile.longitude);
//                    swTile = new LatLng(swP.getWgLat(), swP.getWgLon());
//                    neTile = new LatLng(neP.getWgLat(), neP.getWgLon());
//                    int numberRow = powZoom-row-1;
//
//                    final WmtsTile finalTile = tile;
//                    final LatLng finalSwTile = swTile;
//                    final LatLng finalNeTile = neTile;
//
//
////                    http://imageserver.wochmoc.org.cn/seis/pub/wmts/9/6?service=WMTS&request=GetTile&version=1.0.0&layer=ALL&style=&tilematrixSet=WGS84&format=image%2Fjpeg&transparent=false&height=256&width=256&opacity=1&zIndex=1&rgb=undefined&srs=EPSG%3A4326&tilematrix=15&tilerow=5966&tilecol=27128
//
////                    String url = "http://211.103.189.54:8057/wmts?layer=gw_s&style=default&tilematrixset=WGS84&Service=WMTS&Request=GetTile&Version=1.0.0&Format=tiles&TileMatrix=5&TileCol=26&TileRow=4&wmts.png";
////                    String url = "http://211.103.189.54:8057/wmts?layer=gw_s&style=default&tilematrixset=WGS84&Service=WMTS&Request=GetTile&Version=1.0.0&Format=tiles&TileMatrix=" + (int)zoom +"&TileCol=" + col + "&TileRow=" + row + "&wmts.png";
////                    String url = "http://t7.tianditu.com/DataServer?T=vec_c&x="+col + "&y="+ row +"&l=" + (int)zoom+ "";
////                    String url = "http://t6.tianditu.com/DataServer?T=cva_w&x="+col + "&y="+ row +"&l=" + (int)zoom+ "";
////                    http://imageserver.wochmoc.org.cn/seis/pub/wmts/3/60?service=WMTS&request=GetTile&version=1.0.0&layer=ALL&style=&tilematrixSet=WGS84&format=image%2Fjpeg&transparent=false&height=256&width=256&opacity=1&zIndex=1&rgb=undefined&srs=EPSG%3A4326&tilematrix=13&tilerow=1158&tilecol=6683
//
//
////                    http://172.16.100.74:9090/geoserver/gwc/service/wmts?layer=greatwall%3Agw_9_w&style=&tilematrixset=EPSG%3A900913&Service=WMTS&Request=GetTile&Version=1.0.0&Format=image%2Fpng&TileMatrix=EPSG%3A900913%3A8&TileCol=197&TileRow=100
////                    http://172.16.100.74:9090/geoserver/gwc/service/wmts?layer=greatwall:gw_9_w&style=&tilematrixset=EPSG:900913&Service=WMTS&Request=GetTile&Version=1.0.0&Format=image/png&TileMatrix=EPSG:900913:8&TileCol=197&TileRow=100
//                   String url =  "http://172.16.100.74:9090/geoserver/gwc/service/wmts?layer=greatwall%3Agw_9_w&style=&tilematrixset=EPSG%3A900913&Service=WMTS&Request=GetTile&Version=1.0.0&Format=image%2Fpng&TileMatrix=EPSG%3A900913%3A"+ (int)zoom + "&TileCol=" + col + "&TileRow=" + row;
////                   String url =  "http://imageserver.wochmoc.org.cn/seis/pub/wmts/3/60?service=WMTS&request=GetTile&version=1.0.0&layer=ALL&style=&tilematrixSet=WGS84&format=image%2Fpng&transparent=true&height=256&width=256&opacity=1&zIndex=1&rgb=undefined&srs=EPSG%3A4326&tilematrix=" + (int)zoom + "&tilerow=" + row + "&tilecol=" + col;
////                   String url =  "http://219.234.147.220:18122/wmts?layer=yichandi_dt_hb&style=default&tilematrixset=WGS84&Service=WMTS&Request=GetTile&Version=1.0.0&Format=tiles&TileMatrix=" +(int)zoom + "&TileCol=" + col + "&TileRow=" +row;
//
////                    http://t6.tianditu.com/DataServer?T=cva_w&x=13494&y=6209&l=14
//
//                    Log.e("resource", "resource: " + url );
//
////                    http://t6.tianditu.com/DataServer?T=cva_w&x=13494&y=6209&l=14
//
//                    Glide.with(UiUtils.getContext()).load(url)
//                            .asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
////                            Log.e("resource", "resource: " + url );
//                            if (resource != null){
//                                finalTile.bounds = new LatLngBounds(finalSwTile, finalNeTile);
//                                finalTile.bitmap = resource;
//                                mTitlePathCache.put(key, finalTile);
//                                mTileMap.put(key, finalTile);
//                            }
//                        }
//                    });
//                }else{
//                    mTileMap.put(key,tile);
//                }
//
//            }
//        }
//    }



    public void addTiles(AMap aMap, List<MyGroundOverlay> list) {
        Set<String> keySet = mTileMap.keySet();
        for (String key : keySet) {
            if(isExists(key,list))continue;
            WmtsTile tile = mTileMap.get(key);
            GroundOverlay overlay = aMap.addGroundOverlay(new GroundOverlayOptions().positionFromBounds(tile.bounds).image(BitmapDescriptorFactory.fromBitmap(tile.bitmap)).zIndex(10).visible(false));
            MyGroundOverlay groundOverlay = new MyGroundOverlay();
            groundOverlay.key = key;
            groundOverlay.groundOverlay = overlay;
            groundOverlay.wmtsTile = tile;
            list.add(groundOverlay);
        }
        for(MyGroundOverlay overlay:list){
            overlay.groundOverlay.setVisible(true);
        }
    }

    /**
     * 判断图片是否已经显示
     * @param key
     * @param list
     */
    private boolean isExists(String key, List<MyGroundOverlay> list){
        for(MyGroundOverlay groundOverlay:list){
            if(groundOverlay.key.equals(key))return true;
        }
        return false;
    }

    /**
     * 判断某个key代表的ground overlay是否需要显示
     * @param key
     * @return
     */
    public boolean isInMap(String key){
        return mTileMap.containsKey(key);
    }


    private Point deg2num(double lat, double lon, float zoom) {
        double lat_rad = Math.toRadians(lat);
        double n = (int) Math.pow(2, (int)zoom);
        int xtile = (int) ((lon + 180.0) / 360.0 * n);
        int ytile = (int) ((1.0 - Math.log(Math.tan(lat_rad) + (1 / Math.cos(lat_rad))) / Math.PI) / 2.0 * n);
        return new Point(xtile, ytile);
    }

    private LatLng num2deg(int xtile, int ytile, float zoom) {
        double n = Math.pow(2.0, (int)zoom);
        double lon_deg = xtile / n * 360.0 - 180.0;
        double lat_rad = Math.atan(Math.sinh(Math.PI * (1 - 2 * ytile / n)));
        double lat_deg = Math.toDegrees(lat_rad);
        return new LatLng(lat_deg, lon_deg);
    }


//
//    private Point deg2num(double lat, double lon, float zoom) {
//        double lat_rad = Math.toRadians(lat);
//        double n = (int) Math.pow(2, (int)zoom);
//        int xtile = (int) ((lon + 180.0) / 360.0 * n);
//        int ytile = (int) ((1.0 - Math.log(Math.tan(lat_rad) + (1 / Math.cos(lat_rad))) / Math.PI) / 2.0 * n);
//        return new Point(xtile, ytile);
//    }
//
//    private LatLng num2deg(int xtile, int ytile, float zoom) {
//        double n = Math.pow(2.0, (int)zoom);
//        double lon_deg = xtile / n * 360.0 - 180.0;
//        double lat_rad = Math.atan(Math.sinh(Math.PI * (1 - 2 * ytile / n)));
//        double lat_deg = Math.toDegrees(lat_rad);
//        return new LatLng(lat_deg, lon_deg);
//    }


//     web wgs844326投影显示
//    private LatLng num2deg(int xtile, int ytile, float zoom) {
//        double tileSpan3 = 360/Math.pow(2,(int)zoom);
//        double lon = xtile * tileSpan3 + (-180);
//        double lat = 90 - ytile * tileSpan3;
//        return new LatLng(lat,lon);
//    }

}
