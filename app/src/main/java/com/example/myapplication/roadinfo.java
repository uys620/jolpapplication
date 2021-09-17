package com.example.myapplication;

import java.util.List;

public class roadinfo {
    double time;
    int linkid;
    String linkname;
    String type;
    double start_latitude;
    double start_longitude;
    double final_latitude;
    double final_longitude;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public double getStart_latitude() {
        return start_latitude;
    }

    public void setStart_latitude(double start_latitude) {
        this.start_latitude = start_latitude;
    }

    public double getStart_longitude() {
        return start_longitude;
    }

    public void setStart_longitude(double start_longitude) {
        this.start_longitude = start_longitude;
    }

    public double getFinal_latitude() {
        return final_latitude;
    }

    public void setFinal_latitude(double final_latitude) {
        this.final_latitude =final_latitude;
    }

    public double getFinal_longitude() {
        return final_longitude;
    }

    public void setFinal_longitude(double final_longitude) {
        this.final_longitude = final_longitude;
    }
}
