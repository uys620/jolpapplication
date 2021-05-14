package com.example.myapplication;

public class Graph {
    private int n;
    private int maps[][];

    public Graph(int n){
        this.n=n;
        maps=new int[n+1][n+1];
    }
    public void input(int i,int j,int k,int l){
        maps[i][j]=k;
        maps[j][i]=l;
    }
    public void dijkstra(int v){
        int distance[]=new int[n+1];
        boolean[] check=new boolean[n+1];
        for(int i=1;i<n+1;i++){
            distance[i]=Integer.MAX_VALUE;
        }
        distance[v]=0;
        check[v]=true;
    }
}
