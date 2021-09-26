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

    public int nearid(searchpoint p){
        int id=info.get(0).getLinkname() ;
        double min=(info.get(0).getStart_latitude()-p.getLatitude())*(info.get(0).getStart_latitude()-p.getLatitude())+(info.get(0).getStart_longitude()-p.getLongitude())*(info.get(0).getStart_longitude()-p.getLongitude());
        for(int i=1; i<info.size(); i++){
            if(min>(info.get(i).getStart_latitude()-p.getLatitude())*(info.get(i).getStart_latitude()-p.getLatitude())+(info.get(i).getStart_longitude()-p.getLongitude())*(info.get(i).getStart_longitude()-p.getLongitude())){
                min=(info.get(i).getStart_latitude()-p.getLatitude())*(info.get(i).getStart_latitude()-p.getLatitude())+(info.get(i).getStart_longitude()-p.getLongitude())*(info.get(i).getStart_longitude()-p.getLongitude());
                id=info.get(i).getLinkname();
            }
        }
        return id;
    }

    public List<roadinfo>  a_star_algorhitm(ArrayList<roadinfo> R,int startid,int destid){
        roadinfo start_link = null;
        roadinfo end_link = null;
        int count=0;
        roadinfo temp;
        List<roadinfo> answer=new ArrayList<roadinfo>();
        List<roadinfo> road_closers=new ArrayList<roadinfo>();
        System.out.println("알고리즘 시작");

        for(int i=0;i<R.size();i++){
            if(R.get(i).getLinkname()==startid){
                start_link=R.get(i);
                //count++;
                //start_link.setLinkname(R.get(i).getLinkname());
                break;
            }
        }
        System.out.println("시작링크 검색완료"+ start_link.getLinkname());

        answer.add(start_link);
        System.out.println("시작링크 포함확인"+ answer.get(0).getLinkname());
        //System.out.println("시작링크 포함완료");문제없음
        for(int i=0;i<R.size();i++){
            if(R.get(i).getLinkname()==destid){
                end_link=R.get(i);
                //count++;
                break;
            }
        }
        System.out.println("도착링크 검색완료"+end_link.getLinkname());
        //System.out.println("Rsize"+R.size()); 문제없음

        while(start_link!=end_link){//start_link를 바꿔가면서 끝으로 갈것..


            int check=0;
            for(int i=0;i<R.size();i++){
                if(((R.get(i).getStart_longitude()-start_link.getFinal_longitude())*(R.get(i).getStart_longitude()-start_link.getFinal_longitude())+(R.get(i).getStart_latitude()-start_link.getFinal_latitude())*(R.get(i).getStart_latitude()-start_link.getFinal_latitude()))<0.00000034) {//300말고 적당한값  찾기
                    for(int k=0;k<answer.size();k++){
                        if(R.get(i).getLinkname()%2==1&&R.get(i).getLinkname()+1==answer.get(k).getLinkname())//이부분 바꿔야함 홀수짝수일때 나눠서
                            check++;
                        else if(R.get(i).getLinkname()%2==0&&R.get(i).getLinkname()-1==answer.get(k).getLinkname())
                            check++;
                    }
                    if(check==0){
                        temp=R.get(i);
                        System.out.println(temp.getLinkname());
                        road_closers.add(temp);
                    }
                    //System.out.println("반복중if내");
                }
                //System.out.println("반복중if외부");
                check=0;
            }//범위 내인 도로 road_closer 리스트에 전부 있음
            if(road_closers.size()==0){
                System.out.println("최단거리 찾기 실패");
                break;
            }

            roadinfo shortest=road_closers.get(0);
            System.out.println("반복1끝~~~~~~~~~~~~~~~~~~~~~~~"+road_closers.size());
            for(int m=0;m<road_closers.size();m++)
                System.out.println("closers id"+road_closers.get(m).getLinkname());
            for(int j=0;j<road_closers.size();j++){//time+거리/속도(속도정하기,좌표기준으로 정해야하는데 흐음...)
                if((shortest.getTime()+Math.sqrt((end_link.getStart_latitude()-shortest.getFinal_latitude())*(end_link.getStart_latitude()-shortest.getFinal_latitude())+(end_link.getStart_longitude()-shortest.getStart_longitude())*(end_link.getStart_longitude()-shortest.getStart_longitude()))/0.00015841755)>(road_closers.get(j).getTime()+Math.sqrt((end_link.getStart_latitude()-road_closers.get(j).getFinal_latitude())*(end_link.getStart_latitude()-road_closers.get(j).getFinal_latitude())+(end_link.getStart_longitude()-road_closers.get(j).getStart_longitude())*(end_link.getStart_longitude()-road_closers.get(j).getStart_longitude()))/0.00015841755)){//60말고 적당한값 찾기
                    shortest=road_closers.get(j);
                    //System.out.println("반복중if내");
                }
                //System.out.println("반복중if외부");
            }
            //System.out.println("반복2끝");
            road_closers.clear();
            //System.out.println("answer id2"+answer.get(0).getLinkname());

            answer.add(shortest);
            for(int l=0;l<answer.size();l++)
                System.out.println("answer id"+answer.get(l).getLinkname());
            start_link=shortest;
            System.out.println("start_link id"+start_link.getLinkname());
            count++;

        //1)startid에 맞는 링크가 몇번째(i)인지 찾기(for루프 한번)
        //2)destid에 맞는 링크가 몇번째(j)인지 찾기(for루프 한번){두개한꺼번에 찾자}
        //3)i번째 링크의 끝부분 좌표(final_lat,lon)으로 찾기
        //4)좌표로부터 일정거리(적당한값 찾자) 이내의 시작좌표(start_lat,lon)찾아서 for루프 돌려서 찾기
        //5)그 링크들의 끝부분 좌표(final_lat,lon)찾기
        //6)각 링크들의 시간+(목적지끝부분까지의 거리/평균속도(적당한값 찾자))가 가장 작은것 선택
        //7)선택된 링크의 끝부분좌표 찾기(3과동일)------------------------------------답에 도착할때까지(심각한 오류,길을 따라가다가 가까워졌다가 분기점에서 멀어질 경우 다시 돌아오는 경우 존재(뒤로 돌아오지 않는 방법 필요))
        }
        System.out.println("와일문 탈출"+count);
        answer.add(end_link);
        return answer;
    }
}
