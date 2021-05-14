package com.example.myapplication;

import android.os.AsyncTask;

public class openroadapitask extends AsyncTask<Void,Void,roadinfo> {
    @Override
    public roadinfo doInBackground(Void... params){
        roadAPIclient client=new roadAPIclient();

        roadinfo r=client.getroadinfo();
        return r;
    }
}
