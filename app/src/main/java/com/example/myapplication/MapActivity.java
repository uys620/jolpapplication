package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.LogManager;

public class MapActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        LinearLayout Linearlayout = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        TMapView tmapview = new TMapView(this);
        EditText Search = findViewById(R.id.Search_text);
        EditText SearchStart = findViewById(R.id.SearchStart_text);
        EditText SearchDest = findViewById(R.id.SearchDest_text);

        Button a_star=findViewById(R.id.a_star);
        Button roadSearchBtn=findViewById(R.id.roadSearch);


        Button Searchbtn = findViewById(R.id.Search_btn);
        String SearchString = getIntent().getExtras().getString("SearchText"),
                SearchStartString = getIntent().getExtras().getString("SearchStartText"),
                SearchDestString = getIntent().getExtras().getString("SearchDestText");
        float x;
        TMapData tmapdata = new TMapData();
        int flag = getIntent().getExtras().getInt("sd");
        searchpoint startP = new searchpoint(0,0);
        searchpoint destP = new searchpoint(0,0);
        int count = 2;








        Search.setText(SearchString);
        SearchStart.setText(SearchStartString);
        SearchDest.setText(SearchDestString);


        roadSearchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                /*if(flag != 2){
                    // ???????????? ???????????? ????????? ?????????. ?????? ?????????.
                    Toast.makeText(MapActivity.this, "???????????? ???????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }
                else*/{
                    tmapdata.findTitlePOI(SearchStartString, new TMapData.FindTitlePOIListenerCallback() {
                        @Override
                        public void onFindTitlePOI(ArrayList poiItem) {
                            TMapPOIItem item = (TMapPOIItem) poiItem.get(0);
                            TMapMarkerItem markerItem1 = new TMapMarkerItem();
                            TMapPoint tMapPoint1 = new TMapPoint(0, 0);
                            startP.setLatitude(item.getPOIPoint().getLatitude());
                            startP.setLongitude(item.getPOIPoint().getLongitude());

                        }
                    });

                    tmapdata.findTitlePOI(SearchDestString, new TMapData.FindTitlePOIListenerCallback() {
                        @Override
                        public void onFindTitlePOI(ArrayList poiItem) {
                            TMapPOIItem item = (TMapPOIItem) poiItem.get(0);
                            TMapMarkerItem markerItem1 = new TMapMarkerItem();

                            markerItem1.setPosition(0.5f, 1.0f);
                            TMapPoint tMapPoint1 = new TMapPoint(0, 0);

                            destP.setLatitude(item.getPOIPoint().getLatitude());
                            destP.setLongitude(item.getPOIPoint().getLongitude());

                        }
                    });


                    // ??????????????? ????????? ??????????????????
                    openroadapitask t=new openroadapitask();


                    try{
                        ArrayList<roadinfo> roadinfoArry = t.execute().get();
                        Graph g= new Graph();
                        g.input(roadinfoArry);
                        //System.out.println(startP.getMin_Value());
                        System.out.println("????????????, ????????????:" + startP.getMin_Value() + "," + destP.getMin_Value());
                        List<Integer> route = g.dijkstra(startP.getMin_Value(),destP.getMin_Value());
                        //List<Integer> route = g.dijkstra(1,31);
                        //System.out.println(route.size() + ",");

                        ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
                        for(int i = 0; i < route.size(); i++){
                            searchpoint s = new searchpoint(0,0);
                            System.out.println("??????????????????:" + s.getX(route.get(i)) + "," + s.getY(route.get(i)));
                            alTMapPoint.add( new TMapPoint( s.getX(route.get(i)), s.getY(route.get(i))) );
                        }


                        {
                            TMapPolyLine tMapPolyLine = new TMapPolyLine();
                            tMapPolyLine.setLineColor(Color.BLUE);
                            tMapPolyLine.setLineWidth(2);

                            for (int i = 0; i < alTMapPoint.size(); i++) {
                                tMapPolyLine.addLinePoint(alTMapPoint.get(i));
                            }
                            tmapview.addTMapPolyLine("Line1", tMapPolyLine);
                        }


//            for(int i = 0; i < roadinfoArry.size(); i++) {
//                if(roadinfoArry.get(i).getLinkid()==1024741){
//                    g.input(roadinfoArry);
//                }
//
//                System.out.println("??????????????????:" + roadinfoArry.get(i).getLinkname());
//            }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    //  System.out.println("?????? ??????:" + startP.getLatitude() + "," + startP.getLongitude());
                    // System.out.println("?????? ??????:" + destP.getLatitude() + "," + destP.getLongitude());


                }
            }
        });



        a_star.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                /*if(flag != 2){
                    // ???????????? ???????????? ????????? ?????????. ?????? ?????????.
                    Toast.makeText(MapActivity.this, "???????????? ???????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }
                else*/{
                    tmapdata.findTitlePOI(SearchStartString, new TMapData.FindTitlePOIListenerCallback() {
                        @Override
                        public void onFindTitlePOI(ArrayList poiItem) {
                            TMapPOIItem item = (TMapPOIItem) poiItem.get(0);
                            TMapMarkerItem markerItem1 = new TMapMarkerItem();
                            TMapPoint tMapPoint1 = new TMapPoint(0, 0);
                            startP.setLatitude(item.getPOIPoint().getLatitude());
                            startP.setLongitude(item.getPOIPoint().getLongitude());

                        }
                    });

                    tmapdata.findTitlePOI(SearchDestString, new TMapData.FindTitlePOIListenerCallback() {
                        @Override
                        public void onFindTitlePOI(ArrayList poiItem) {
                            TMapPOIItem item = (TMapPOIItem) poiItem.get(0);
                            TMapMarkerItem markerItem1 = new TMapMarkerItem();

                            markerItem1.setPosition(0.5f, 1.0f);
                            TMapPoint tMapPoint1 = new TMapPoint(0, 0);

                            destP.setLatitude(item.getPOIPoint().getLatitude());
                            destP.setLongitude(item.getPOIPoint().getLongitude());

                        }
                    });


                    // a*???????????? ??????
                    openroadapitask t=new openroadapitask();


                    try{
                        ArrayList<roadinfo> roadinfoArry = t.execute().get();
                        //Graph g= new Graph();
                        a_star a=new a_star();
                        //g.input(roadinfoArry);
                        a.input(roadinfoArry);
                        //System.out.println(startP.getMin_Value());
                        //System.out.println("????????????, ????????????:" + startP.getMin_Value() + "," + destP.getMin_Value());
                        //List<Integer> route = g.dijkstra(startP.getMin_Value(),destP.getMin_Value());
                        int startid=a.nearid(startP);
                        int destid=a.nearid(destP);
                        System.out.println("???????????? id "+startid);
                        System.out.println("????????? id "+destid);
                        List<roadinfo> route = a.a_star_algorhitm(roadinfoArry, startid,destid);

                        //List<Integer> route = g.dijkstra(1,31);
                        //System.out.println(route.size() + ",");

                        ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
                        for(int i = 0; i < route.size(); i++){
                            //searchpoint s = new searchpoint(0,0);
                            //System.out.println("??????????????????:" + s.getX(route.get(i)) + "," + s.getY(route.get(i)));
                            alTMapPoint.add( new TMapPoint(route.get(i).getStart_latitude(),route.get(i).getStart_longitude()));
                        }


                        {
                            TMapPolyLine tMapPolyLine = new TMapPolyLine();
                            tMapPolyLine.setLineColor(Color.BLUE);
                            tMapPolyLine.setLineWidth(2);

                            for (int i = 0; i < alTMapPoint.size(); i++) {
                                tMapPolyLine.addLinePoint(alTMapPoint.get(i));
                            }
                            tmapview.addTMapPolyLine("Line1", tMapPolyLine);
                        }


//            for(int i = 0; i < roadinfoArry.size(); i++) {
//                if(roadinfoArry.get(i).getLinkid()==1024741){
//                    g.input(roadinfoArry);
//                }
//
//                System.out.println("??????????????????:" + roadinfoArry.get(i).getLinkname());
//            }
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }


                    //  System.out.println("?????? ??????:" + startP.getLatitude() + "," + startP.getLongitude());
                    // System.out.println("?????? ??????:" + destP.getLatitude() + "," + destP.getLongitude());


                }
            }
        });

        Searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("SearchText", Search.getText().toString());
                intent.putExtra("SearchStartText", SearchStart.getText().toString());
                intent.putExtra("SearchDestText", SearchDest.getText().toString());
                if(flag == 2){
                    intent.putExtra("sd",0);
                }
                else {
                    intent.putExtra("sd", flag);
                }
                startActivity(intent);
                finish();
            }
        });


        tmapview.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
            @Override
            public boolean onPressEvent(ArrayList arrayList, ArrayList arrayList1, TMapPoint tMapPoint, PointF pointF) {

                return false;
            }

            @Override
            public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {

                //Toast.makeText(MapActivity.this, "onPressUp~!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
            @Override
            public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                if ( flag == 0 || flag == 2) {
                    intent.putExtra("SearchStartText", tMapMarkerItem.getCalloutTitle());
                    intent.putExtra("SearchText", Search.getText().toString());
                    intent.putExtra("SearchDestText", SearchDest.getText().toString());
                    intent.putExtra("sd",1);
                } else {
                    intent.putExtra("SearchDestText", tMapMarkerItem.getCalloutTitle());
                    intent.putExtra("SearchText", Search.getText().toString());
                    intent.putExtra("SearchStartText", SearchStart.getText().toString());
                    intent.putExtra("sd",2);
                }
                startActivity(intent);
                finish();
            }
        });


        //??????
        tmapview.setSKTMapApiKey("l7xx2949b2e5de904dcaa74e3ffcdbe29864");
        tmapview.setCompassMode(true);
        tmapview.setIconVisibility(true);
        tmapview.setZoomLevel(13);
        tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
        tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmapview.setTrackingMode(false);
        tmapview.setSightVisible(true);
        Linearlayout.addView(tmapview);
        tmapview.setCenterPoint(127.031088, 37.498976);

        if(flag != 2) {
            tmapdata.findAllPOI(SearchString, 100, new TMapData.FindAllPOIListenerCallback() {
                @Override
                public void onFindAllPOI(ArrayList poiItem) {
                    for (int i = 0; i < poiItem.size(); i++) {
                        TMapPOIItem item = (TMapPOIItem) poiItem.get(i);
                        //TMapPoint tMapPoint1=new TMapPoint(item.getPOIPoint().getLatitude(),item.getPOIPoint().getLongitude());
                        TMapMarkerItem markerItem1 = new TMapMarkerItem();//asljdflk;ajsdf
                        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_edit);

                        Bitmap bitmap1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.side);
                        bitmap1 = Bitmap.createScaledBitmap(bitmap1, 50, 50, false);
                        markerItem1.setIcon(bitmap); // ?????? ????????? ??????
                        markerItem1.setPosition(0.5f, 1.0f); // ????????? ???????????? ??????, ???????????? ??????
                        TMapPoint tMapPoint1 = new TMapPoint(0, 0);

                        tMapPoint1.setLatitude(item.getPOIPoint().getLatitude());
                        tMapPoint1.setLongitude(item.getPOIPoint().getLongitude());

                        markerItem1.setCalloutTitle(((TMapPOIItem) poiItem.get(i)).getPOIName());//???????????????
                        markerItem1.setCalloutSubTitle(((TMapPOIItem) poiItem.get(i)).getPOIAddress());
                        markerItem1.setCanShowCallout(true);
                        //markerItem1.setAutoCalloutVisible(true);

                        markerItem1.setCalloutRightButtonImage(bitmap1);
                        markerItem1.setCalloutLeftImage(bitmap1);


                        markerItem1.setTMapPoint(tMapPoint1); // ????????? ?????? ??????
                        tmapview.addMarkerItem("markerItem1" + i, markerItem1); // ????????? ?????? ??????
                    }
                }
            });
        }
        else{
            tmapdata.findTitlePOI(SearchStartString, new TMapData.FindTitlePOIListenerCallback() {
                @Override
                public void onFindTitlePOI(ArrayList poiItem) {
                    TMapPOIItem item = (TMapPOIItem) poiItem.get(0);
                    TMapMarkerItem markerItem1 = new TMapMarkerItem();//asljdflk;ajsdf
                    Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_edit);

                    Bitmap bitmap1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.side);
                    bitmap1 = Bitmap.createScaledBitmap(bitmap1, 50, 50, false);
                    markerItem1.setIcon(bitmap); // ?????? ????????? ??????
                    markerItem1.setPosition(0.5f, 1.0f); // ????????? ???????????? ??????, ???????????? ??????
                    TMapPoint tMapPoint1 = new TMapPoint(0, 0);

                    tMapPoint1.setLatitude(item.getPOIPoint().getLatitude());
                    tMapPoint1.setLongitude(item.getPOIPoint().getLongitude());

                    markerItem1.setCalloutTitle(((TMapPOIItem) poiItem.get(0)).getPOIName());//???????????????
                    markerItem1.setCalloutSubTitle(((TMapPOIItem) poiItem.get(0)).getPOIAddress());
                    markerItem1.setCanShowCallout(true);
                    //markerItem1.setAutoCalloutVisible(true);

                    markerItem1.setCalloutRightButtonImage(bitmap1);
                    markerItem1.setCalloutLeftImage(bitmap1);



                    markerItem1.setTMapPoint(tMapPoint1); // ????????? ?????? ??????
                    tmapview.addMarkerItem("markerItem2", markerItem1); // ????????? ?????? ??????
                }
            });

            tmapdata.findTitlePOI(SearchDestString, new TMapData.FindTitlePOIListenerCallback() {
                @Override
                public void onFindTitlePOI(ArrayList poiItem) {
                    TMapPOIItem item = (TMapPOIItem) poiItem.get(0);
                    TMapMarkerItem markerItem1 = new TMapMarkerItem();//asljdflk;ajsdf
                    Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_edit);

                    Bitmap bitmap1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.side);
                    bitmap1 = Bitmap.createScaledBitmap(bitmap1, 50, 50, false);
                    markerItem1.setIcon(bitmap); // ?????? ????????? ??????
                    markerItem1.setPosition(0.5f, 1.0f); // ????????? ???????????? ??????, ???????????? ??????
                    TMapPoint tMapPoint1 = new TMapPoint(0, 0);

                    tMapPoint1.setLatitude(item.getPOIPoint().getLatitude());
                    tMapPoint1.setLongitude(item.getPOIPoint().getLongitude());

                    markerItem1.setCalloutTitle(((TMapPOIItem) poiItem.get(0)).getPOIName());//???????????????
                    markerItem1.setCalloutSubTitle(((TMapPOIItem) poiItem.get(0)).getPOIAddress());
                    markerItem1.setCanShowCallout(true);
                    //markerItem1.setAutoCalloutVisible(true);

                    markerItem1.setCalloutRightButtonImage(bitmap1);
                    markerItem1.setCalloutLeftImage(bitmap1);




                    markerItem1.setTMapPoint(tMapPoint1); // ????????? ?????? ??????
                    tmapview.addMarkerItem("markerItem3", markerItem1); // ????????? ?????? ??????
                }
            });
        }





    }




}

