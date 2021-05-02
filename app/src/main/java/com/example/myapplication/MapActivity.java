package com.example.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.myapplication.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Elizabeth on 2016-09-22.
 */
public class MapActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //선언
        RelativeLayout relativeLayout = new RelativeLayout(this);
        TMapView tmapview = new TMapView(this);


        TMapMarkerItem markerItem1 = new TMapMarkerItem();//asljdflk;ajsdf
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.common_google_signin_btn_icon_dark);
        markerItem1.setIcon(bitmap); // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정



        //키값
        tmapview.setSKTMapApiKey("l7xx2949b2e5de904dcaa74e3ffcdbe29864");

        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(15);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(true);
        tmapview.setSightVisible(true);
        relativeLayout.addView(tmapview);
        setContentView(relativeLayout);


        
        TMapData tmapdata = new TMapData();
        tmapdata.findAllPOI("은마아파트",100, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList poiItem) {
                for(int i = 0; i < poiItem.size(); i++) {
                    TMapPOIItem  item = (TMapPOIItem) poiItem.get(i);
                    TMapPoint tMapPoint1=new TMapPoint(item.getPOIPoint().getLatitude(),item.getPOIPoint().getLongitude());
                    markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
                    tmapview.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가
                }
            }
        });

        
    }

}

