package com.example.myapplication;

import java.util.List;

public class roadinfo {
    Double time;
    int linkid;
    String linkname;
    /*List<double> Longitude;
    List<double> Latitude;
    public roadinfo(){
        time = 0.0;
        linkid = 0;
        linkname = "";
        Longitude = new List<double>();
        Latitude = new List<double>();
    }*/
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    int index;

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getLinkid() {
        return linkid;
    }

    public void setLinkid(int linkid) {
        this.linkid = linkid;
    }

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }
}
