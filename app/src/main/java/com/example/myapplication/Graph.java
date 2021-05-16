package com.example.myapplication;


import java.util.ArrayList;

public class Graph {
    private int n,i,j;
    private double maps[][];

    public Graph(){
        this.n=34;
        //maps=new double[n+1][n+1];
        maps=new double[][]{  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//0번
                {0,0,0,0,0,1024741,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//1번
                {0,0,0,0,0,0,1057861,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//2
                {0,0,0,0,0,0,0,1057841,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//3
                {0,0,0,0,0,1026162,0,0,0,0,0,0,0,0,1026371,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//4
                {0,1024742,0,0,1026161,0,1025912,0,0,0,0,0,0,0,0,1026171,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//5
                {0,0,1057862,0,0,1025911,0,1025372,0,0,1064111,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//6
                {0,0,0,1057842,0,0,1025371,0,1057872,0,0,1064181,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//7
                {0,0,0,0,0,0,0,1057871,0,0,0,0,1064221,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//8
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//9
                {0,0,0,0,0,0,1064112,0,0,0,0,1064151,0,0,0,0,1064121,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//10
                {0,0,0,0,0,0,0,1064182,0,0,1064152,0,1064161,0,0,0,0,1064191,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//11
                {0,0,0,0,0,0,0,0,1064222,0,0,1064162,0,0,0,0,0,0,1064231,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//12
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1027772,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//13
                {0,0,0,0,1026372,0,0,0,0,0,0,0,0,1027771,0,1027312,0,0,0,0,0,0,0,1027781,0,0,0,0,0,0,0,0,0,0,0},//14
                {0,0,0,0,0,1026172,0,0,0,0,0,0,0,0,1027311,0,1026892,0,0,0,0,0,0,0,1027321,0,0,0,0,0,0,0,0,0,0},//15
                {0,0,0,0,0,0,0,0,0,0,1064122,0,0,0,0,1026891,0,1026432,0,0,1064131,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//16
                {0,0,0,0,0,0,0,0,0,0,0,1064192,0,0,0,0,1026431,0,1057912,0,0,1064201,0,0,0,0,0,0,0,0,0,0,0,0,0},//17
                {0,0,0,0,0,0,0,0,0,0,0,0,1064232,0,0,0,0,1057911,0,1058021,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//18
                {0,0,0,0,0,0,0,0,0,1025042,0,0,0,0,0,0,0,0,1058022,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//19
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1064132,0,0,0,0,1064171,0,0,0,1064141,0,0,0,0,0,0,0,0,0},//20
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1064202,0,0,1064172,0,0,0,0,0,1064211,0,0,0,0,0,0,0,0},//21
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1028632,0,0,0,0,0,0,0,0,0,0,0},//22
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1027782,0,0,0,0,0,0,0,1028631,0,1028282,0,0,0,1028641,0,0,0,0,0,0},//23
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1027322,0,0,0,0,0,0,0,1028281,0,1027812,0,0,0,1028291,0,0,0,0,0},//24
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1064142,0,0,0,1027811,0,1027242,0,0,0,1027821,0,0,0,0},//25
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1064212,0,0,0,1027241,0,1026742,0,0,0,1027251,0,0,0},//26
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1026741,0,0,0,0,0,0,0,0},//27
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1028642,0,0,0,0,0,0,0,0,0,0,0},//28
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1028292,0,0,0,0,0,1028612,0,0,0,0},//29
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1027822,0,0,0,1028611,0,1028252,0,1028621,0},//30
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1027252,0,0,0,1028251,0,1027732,0,1028261},//31
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1027731,0,0,0},//32
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1028622,0,0,0,0},//33
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1028262,0,0,0}//34
        };


    }
    public void input( ArrayList<roadinfo> r){
        for(i=0;i<n+1;i++){
            for(j=0;j<n+1;j++){
                if(maps[i][j]!=0){
                    for(int x=0; x<r.size(); x++){
                        if(Integer.parseInt(r.get(x).getLinkname()) == maps[i][j]){
                        maps[i][j]=r.get(x).getTime();
                        }
                    }
                }
            }
        }
    }
    public void dijkstra(int v){
        double distance[]=new double[n+1];
        boolean[] check=new boolean[n+1];
        for(int i=1;i<n+1;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        distance[v]=0;
        check[v]=true;
        for(i=1;i<n+1;i++){
            if(!check[i]&&maps[v][i]!=0){
                distance[i]=maps[v][i];
            }
        }

        for(int a=0;a<n-1;a++){
            //원래는 모든 노드가 true될때까지 인데
            //노드가 n개 있을 때 다익스트라를 위해서 반복수는 n-1번이면 된다.
            //원하지 않으면 각각의 노드가 모두 true인지 확인하는 식으로 구현해도 된다.
            double min=Integer.MAX_VALUE;
            int min_index=-1;

            //최소값 찾기
            for(int i=1;i<n+1;i++){
                if(!check[i] && distance[i]!=Integer.MAX_VALUE){
                    if(distance[i]<min ){
                        min=distance[i];
                        min_index = i;
                    }
                }
            }

            check[min_index] = true;
            for(int i=1;i<n+1;i++){
                if(!check[i] && maps[min_index][i]!=0){
                    if(distance[i]>distance[min_index]+maps[min_index][i]){
                        distance[i] = distance[min_index]+maps[min_index][i];
                    }
                }
            }

        }

        System.out.println(distance[2]+" ");
    }

}
