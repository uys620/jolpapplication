package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class postActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private EditText mTitle;
    private EditText mContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mTitle=findViewById(R.id.post_title_edit);
        mContents=findViewById(R.id.post_contents_edit);

        findViewById(R.id.post_save_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mAuth.getCurrentUser()!=null){
            String postID=mStore.collection(FirebaseID.post).document().getId();
            Map<String,Object> data=new HashMap<>();
            data.put(FirebaseID.documentID,mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.title,mTitle.getText().toString());
            data.put(FirebaseID.contents,mContents.getText().toString());
            mStore.collection(FirebaseID.post).document(postID).set(data, SetOptions.merge());
            finish();
        }
    }
}