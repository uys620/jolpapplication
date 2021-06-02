package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.model.post;
import com.example.myapplication.adapters.postadapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {


    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private postadapter mAdapter;
    private List<post> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mPostRecyclerView=findViewById(R.id.main_recyclerview);

        findViewById(R.id.main_post_edit).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),postActivity.class);
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
        mStore.collection(FirebaseID.post)//파이어스토어에서 post로 되어있는 collectionreference 만들기
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)//시간순서대로 오름차순
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value!=null){
                            mDatas.clear();
                        for(DocumentSnapshot snap: value.getDocuments()){//for-each문 value.getDocument()에 저장된 값을 DocumentSnapshot 형식의 snap을 통해 가져옴
                            Map<String,Object> shot=snap.getData();//key는 string ,value는 object인 맵함수, value.getDocument의 정보 불러옴
                            String documentID=String.valueOf(shot.get(FirebaseID.documentID));//key:String에 해당하는 Object를 가져와서 스트링으로 바꿔서 사용
                            String nickname=String.valueOf(shot.get((FirebaseID.nickname)));
                            String title= String.valueOf(snap.get(FirebaseID.title));
                            String contents=String.valueOf(shot.get(FirebaseID.contents));
                            post data=new post(documentID,nickname,title,contents);
                            mDatas.add(data);
                        }
                            mAdapter=new postadapter(mDatas);//mdatas를 madapter에 저장
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }



    /*@Override
    public void onItemClick(View view, int position){
        Intent intent = new Intent(this,Post2Activity.class);
        intent.putExtra(FirebaseID.documentID,mDatas.get(position).getDocumentId());
        startActivity(intent);
    }*/
}