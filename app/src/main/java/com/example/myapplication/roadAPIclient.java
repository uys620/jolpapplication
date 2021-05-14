package com.example.myapplication;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class roadAPIclient {
    final static String roadAPIURL="https://apis.openapi.sk.com/tmap/traffic?version=1.67&reqCoordType=WGS84GEO&resCoordType=WGS84GEO&trafficType=AUTO&zoomLevel=17&callback=application/json&appKey=l7xx2949b2e5de904dcaa74e3ffcdbe29864&centerLat=37.504500&centerLon=127.049000";
    public ArrayList<roadinfo> getroadinfo(){
        ArrayList<roadinfo> roadinfoArry = new ArrayList<roadinfo>();
        roadinfo r=new roadinfo();
        String urlString=roadAPIURL;

        try{
            URL url=new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));
            roadinfoArry = parseJSON(json);
        }catch(MalformedURLException e){
            System.err.println("Malformed URL");
            e.printStackTrace();
            return null;
        }catch(JSONException e) {
            System.err.println("JSON parsing error");
            e.printStackTrace();
            return null;
        }catch(IOException e){
            System.err.println("URL Connection failed");
            e.printStackTrace();
            return null;
        }

        return roadinfoArry;

    }

    private ArrayList<roadinfo> parseJSON(JSONObject json) throws JSONException{
        ArrayList<roadinfo> roadinfoArry = new ArrayList<roadinfo>();
        JSONArray jArry = json.getJSONArray("features");
        for(int i = 0; i < jArry.length(); i++) {
            roadinfo r = new roadinfo();
            r.setLinkname(jArry.getJSONObject(i).getJSONObject("properties").getString("id"));
            r.setTime(jArry.getJSONObject(i).getJSONObject("properties").getDouble("time"));
            r.setIndex(jArry.getJSONObject(i).getJSONObject("properties").getInt("index"));
            roadinfoArry.add(r);
        }
        return roadinfoArry;
    }

    private static String getStringFromInputStream(InputStream is){
        BufferedReader br=null;
        StringBuilder sb =new StringBuilder();

        String line;
        try{
            br=new BufferedReader(new InputStreamReader(is));
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
