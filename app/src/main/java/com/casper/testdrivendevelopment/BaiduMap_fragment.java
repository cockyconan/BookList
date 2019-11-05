package com.casper.testdrivendevelopment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;

import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaiduMap_fragment extends Fragment {
    private MapView mMapView = null;

    public BaiduMap_fragment() {
        // Required empty public constructor
    }
    public void load(final Handler handler,final String url)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_baidu_map_fragment, container, false);


        mMapView = (MapView) view.findViewById(R.id.bmapView);
        final BaiduMap mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //设定中心点坐标
        LatLng cenpt =  new LatLng(22.255939,113.54092);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                //要移动的点
                .target(cenpt)
                //放大地图到20倍
                .zoom(15)
                .build();

        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);

        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mMapView.setLogoPosition(LogoPosition.logoPostionCenterTop);

        final ShopSetter shopSetter=new ShopSetter();


        Handler handler = new Handler(){
          public void handleMessage(Message msg)
          {
              super.handleMessage(msg);

              ArrayList<Shop> shops = shopSetter.getShop();


              double Longitude,Latitudde;
              String Name,Memo;

              for (int i = 0; i < shops.size(); i++) {
                  Longitude=shops.get(i).getLongitude();
                  Latitudde=shops.get(i).getLatitude();
                  Name=shops.get(i).getName();
                  Memo=shops.get(i).getMemo();

                  LatLng point = new LatLng( Latitudde,Longitude);
                  BitmapDescriptor bitmap = BitmapDescriptorFactory
                          .fromResource(R.mipmap.ic_launcher_round);
                  //构建MarkerOption，用于在地图上添加Marker，地图上的自定义的标签和
                  OverlayOptions option = new MarkerOptions()
                          .position(point)
                          .icon(bitmap);
                  OverlayOptions textoption=new TextOptions().bgColor(0xAAFFFF00).fontSize(50).fontColor(0xFFFF00FF).text(Name).position(point);
                  mBaiduMap.addOverlay(option);
                  mBaiduMap.addOverlay(textoption);
                  //设置logo





          }//执行增加图层
              ;
          }
        };
        shopSetter.load(handler,"http://file.nidama.net/class/mobile_develop/data/bookstore.json");
        
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

}
