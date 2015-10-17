package com.guan.o2o.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.guan.o2o.R;
import com.guan.o2o.utils.FuncUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ServiceAreaActivity extends FrameActivity {

    @InjectView(R.id.bmapView)
    MapView bmapView;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    private BaiduMap mBaiduMap;
    private boolean isFirstLoc = true;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 在使用百度地图SDK各组件之前初始化context信息,传入ApplicationContext
         */
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_service_area);
        ButterKnife.inject(this);

        /**
         * 初始化变量
         */
        initVariable();
        /**
         * 绑定数据
         */
        bindData();
    }


    /**
     * 初始化变量
     */
    private void initVariable() {
        tvTitle.setText(getString(R.string.service_scope));
        mBaiduMap = bmapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        // 注册监听函数
        mLocationClient.registerLocationListener(myListener);
        // 初始化位置
        mLocationClient.setLocOption(FuncUtil.initLocation());
    }

    /**
     * 绑定数据
     */
    private void bindData() {
        // 开启定位
        mLocationClient.start();
    }

    /**
     * 监听实现
     */
    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // mapview销毁后不在处理新接收的位置
            if (location == null || bmapView == null)
                return;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                            // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
//            showMsg(location.getProvince() + location.getCity() + location.getStreet());
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mBaiduMap.animateMapStatus(u);
            }

            /**
             * 标注覆盖物
             */
            //定义Maker坐标点
            LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.ic_location_arrow);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(point)
                    .icon(bitmap);
            // 设置比例尺等级,范围是3-20,等级越小比例尺越大
            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(point, 15);
            // 改变百度地图的状态
            mBaiduMap.setMapStatus(msu);
            //在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    /**
     * 在activity执行onResume时执行mMapView.onResume(),实现地图生命周期管理
     */
    @Override
    protected void onResume() {
        super.onResume();
        bmapView.onResume();
    }

    /**
     * 在activity执行onPause时执行mMapView.onPause(),实现地图生命周期管理
     */
    @Override
    protected void onPause() {
        super.onPause();
        bmapView.onPause();
    }

    /**
     * 在activity执行onDestroy时执行mMapView.onDestroy(),实现地图生命周期管理
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        mLocationClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        bmapView.onDestroy();
        bmapView = null;
    }

}
