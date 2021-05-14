package com.example.myapplication;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class roadAPIclient {
    final static String roadAPIURL="https://apis.openapi.sk.com/tmap/traffic?version=1.67&reqCoordType=WGS84GEO&resCoordType=WGS84GEO&trafficType=AUTO&zoomLevel=17&callback=application/json&appKey=l7xx2949b2e5de904dcaa74e3ffcdbe29864&centerLat=37.504500&centerLon=127.049000";
    public roadinfo getroadinfo(){
        roadinfo r=new roadinfo();
        String urlString=roadAPIURL;

        try{
            URL url=new URL(urlString);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));
            r = parseJSON(json);
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

        return r;

    }
    private roadinfo parseJSON(JSONObject json) throws JSONException{//여기서 오류
        roadinfo r=new roadinfo();
        r.setLinkname(json.getJSONObject("features").getJSONObject("properties").getString("name"));
        r.setTime(json.getJSONObject("features").getJSONObject("properties").getDouble("time"));
        r.setIndex(json.getJSONObject("featrues").getJSONObject("properties").getInt("index"));
        return r;
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
