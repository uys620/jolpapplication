package com.example.myapplication;

import android.os.AsyncTask;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class openroadapitask extends AsyncTask<Void,Void,ArrayList<roadinfo>> {
    @Override
    public ArrayList<roadinfo> doInBackground(Void... params){
        roadAPIclient client=new roadAPIclient();
        ArrayList<roadinfo> roadinfoArry = new ArrayList<roadinfo>();
        roadinfoArry = client.getroadinfo();
        return roadinfoArry;
    }
}
