package com.gxsn.gaodemapdemo.gaode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.MyLocationStyle;
import com.gxsn.gaodemapdemo.R;
import com.gxsn.gaodemapdemo.gaode.wmts.MyGroundOverlay;
import com.gxsn.gaodemapdemo.gaode.wmts.WmtsTileProvider;
import com.gxsn.gaodemapdemo.google.MapsActivity;
import com.gxsn.gaodemapdemo.tianditu.TianDiTuMapActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AMap.OnCameraChangeListener {

    private MapView mMapview;
    private AMap aMap;
    private WmtsTileProvider wmtsTileProvider;
    List<MyGroundOverlay> mGroundOverlays;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapview = ((MapView) findViewById(R.id.mv_map));

        mImage = ((ImageView) findViewById(R.id.iv_iamge));


        ((AppCompatButton) findViewById(R.id.acb_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

        findViewById(R.id.acb_btn_tian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TianDiTuMapActivity.class));
            }
        });

        mMapview.onCreate(savedInstanceState);
        initAMap();

        setAMap();


//        //加载自定义wms
//        HeritageScopeTileProvider tileProvider = new HeritageScopeTileProvider();
//        aMap.addTileOverlay(new TileOverlayOptions()
//                .tileProvider(tileProvider));
        wmtsTileProvider = new WmtsTileProvider();

        aMap.setOnCameraChangeListener(this);

    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapview.onSaveInstanceState(outState);
    }
    /**
     * 地图方法
     */
    private void setAMap() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(3);
        //设置默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);

        aMap.getUiSettings().setScaleControlsEnabled(true);// 设置比例尺
    }




    /**
     * 初始化AMap对象
     */
    private void initAMap() {
        if (aMap == null) {
            aMap = mMapview.getMap();
        }
        setUpMap();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapview.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mMapview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapview.onDestroy();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
//        if (cameraPosition.zoom  > 16){
            wmtsTileProvider.upateTiles(aMap.getProjection().getVisibleRegion().latLngBounds, cameraPosition.zoom);
            if (mGroundOverlays != null) {
                Iterator<MyGroundOverlay> iterator = mGroundOverlays.listIterator();
                while (iterator.hasNext()) {
                    MyGroundOverlay groundOverlay = iterator.next();
                    if (!wmtsTileProvider.isInMap(groundOverlay.key)) {
                        groundOverlay.groundOverlay.remove();
                        iterator.remove();
                    }
                }
            }
            if (mGroundOverlays == null) mGroundOverlays = new ArrayList<>();
            wmtsTileProvider.addTiles(aMap, mGroundOverlays);
//        }
    }
}
