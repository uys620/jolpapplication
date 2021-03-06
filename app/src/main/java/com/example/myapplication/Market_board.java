package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.postadapter;
import com.example.myapplication.model.post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Market_board extends AppCompatActivity {


    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private postadapter mAdapter;
    private List<post> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_board);

        mPostRecyclerView=findViewById(R.id.market_recyclerview);

        findViewById(R.id.main_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),FrontPageActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.write_marketpost).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MarketPostActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.map).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),MapActivity.class);
                intent.putExtra("SearchText","");
                intent.putExtra("SearchStartText","");
                intent.putExtra("SearchDestText","");
                intent.putExtra("sd",0);
                startActivity(intent);
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas=new ArrayList<>();
        mStore.collection("marketpost")//???????????????????????? post??? ???????????? collectionreference ?????????
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)//?????????????????? ????????????
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value!=null){
                            mDatas.clear();
                            for(DocumentSnapshot snap: value.getDocuments()){//for-each??? value.getDocument()??? ????????? ?????? DocumentSnapshot ????????? snap??? ?????? ?????????
                                Map<String,Object> shot=snap.getData();//key??? string ,value??? object??? ?????????, value.getDocument??? ?????? ?????????
                                String documentID=String.valueOf(shot.get(FirebaseID.documentID));//key:String??? ???????????? Object??? ???????????? ??????????????? ????????? ??????
                                String nickname=String.valueOf(shot.get((FirebaseID.nickname)));
                                String title= String.valueOf(snap.get(FirebaseID.title));
                                String contents=String.valueOf(shot.get(FirebaseID.contents));
                                String postID=String.valueOf(shot.get(FirebaseID.postID));
                                post data=new post(documentID,nickname,title,contents,postID);
                                mDatas.add(data);
                            }
                            mAdapter=new postadapter(mDatas);//mdatas??? madapter??? ??????
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }
}

