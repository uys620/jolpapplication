package com.example.myapplication;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private postadapter mAdapter;
    private List<post> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mPostRecyclerView=findViewById(R.id.main_recyclerview);

        findViewById(R.id.main_post_edit).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas=new ArrayList<>();
        mStore.collection(FirebaseID.post)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult()!=null) {
                                for (DocumentSnapshot snap : task.getResult()) {
                                    Map<String,Object> shot=snap.getData();
                                    String documentID=String.valueOf(shot.get(FirebaseID.documentID));
                                    String title= String.valueOf(snap.get(FirebaseID.title));
                                    String contents=String.valueOf(shot.get(FirebaseID.contents));
                                    post data=new post(documentID,title,contents);
                                    mDatas.add(data);
                                }
                                mAdapter=new postadapter(mDatas);
                                mPostRecyclerView.setAdapter(mAdapter);

                            }

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,postActivity.class));

    }
}