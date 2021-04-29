package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

import javax.annotation.Nonnull;

public class Post2Activity extends AppCompatActivity {
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private TextView mTitleText, mContentsText, mNameText;

    private String id;
    /*
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        mTitleText = findViewById(R.id.post2_title);
        mContentsText = findViewById(R.id.post2_content);
        mNameText = findViewById(R.id.post2_name);

        Intent getIntent = getIntent();
        id = getIntent.getStringExtra(FirebaseID.documentID);
        Log.e("ITEM DOCUMENT ID :", id);

        mStore.collection(FirebaseID.post).document(id)
                .get()
                .addOnCompleteListener(new onCompleteListener<DocumentSnapshot>(){
                    @Override
                    public void onComplete(@Nonnull Task<DocumentSnapshot> task){
                        if(task.isSuccessful()){
                            if(task.getResult()!=null){
                                Map<String,Object> snap = task.getResult().getData();
                                String title = String.valueOf(snap.get(FirebaseID.title));
                                String contents = String.valueOf(snap.get(FirebaseID.contents));
                                String name = String.valueOf(snap.get(FirebaseID.nickname));

                                mTitleText.setText(title);
                                mContentsText.setText(contents);
                                mNameText.setText(name);
                            }
                        }
                    }
                });
    }*/
}