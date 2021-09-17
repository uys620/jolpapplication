package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class a_star {
    private ArrayList<roadinfo> info;

    public a_star(){

    }

    public void input(ArrayList<roadinfo> r){
        this.info=r;
    }

    public int nearid(ArrayList<roadinfo> r,searchpoint p){
        int id=r.get(0).getLinkid();
        double min=(r.get(0).getStart_latitude()-p.getLatitude())*(r.get(0).getStart_latitude()-p.getLatitude())+(r.get(0).getStart_longitude()-p.getLongitude())*(r.get(0).getStart_longitude()-p.getLongitude());
        for(int i=1; i<r.size(); i++){
            if(min>(r.get(i).getStart_latitude()-p.getLatitude())*(r.get(i).getStart_latitude()-p.getLatitude())+(r.get(i).getStart_longitude()-p.getLongitude())*(r.get(i).getStart_longitude()-p.getLongitude())){
                min=(r.get(i).getStart_latitude()-p.getLatitude())*(r.get(i).getStart_latitude()-p.getLatitude())+(r.get(i).getStart_longitude()-p.getLongitude())*(r.get(i).getStart_longitude()-p.getLongitude());
                id=r.get(i).getLinkid();
            }
        }
        return id;
    }

    public List<Integer> a_star(ArrayList<roadinfo> R,int startid,int destid){
        //1)startid에 맞는 링크가 몇번째(i)인지 찾기(for루프 한번)
        //2)destid에 맞는 링크가 몇번째(j)인지 찾기(for루프 한번){두개한꺼번에 찾자}
        //3)i번째 링크의 끝부분 좌표(final_lat,lon)으로 찾기
        //4)좌표로부터 일정거리(적당한값 찾자) 이내의 시작좌표(start_lat,lon)찾아서 for루프 돌려서 찾기
        //5)그 링크들의 끝부분 좌표(final_lat,lon)찾기
        //6)각 링크들의 시간+(목적지끝부분까지의 거리/평균속도(적당한값 찾자))가 가장 작은것 선택
        //7)선택된 링크의 끝부분좌표 찾기(3과동일)------------------------------------답에 도착할때까지(심각한 오류,길을 따라가다가 가까워졌다가 분기점에서 멀어질 경우 다시 돌아오는 경우 존재(뒤로 돌아오지 않는 방법 필요))
    }
}
