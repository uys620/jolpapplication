package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class MarketPostActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private EditText mTitle;
    private EditText mContents;
    private String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mTitle=findViewById(R.id.post_title_edit);
        mContents=findViewById(R.id.post_contents_edit);
        findViewById(R.id.post_save_button).setOnClickListener(this);
        if(mAuth.getCurrentUser()!=null){
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.getResult()!=null){
                                nickname=(String)task.getResult().getData().get(FirebaseID.nickname);
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        if(mAuth.getCurrentUser()!=null){
            String postID=mStore.collection("marketpost").document().getId();
            Map<String,Object> data=new HashMap<>();
            data.put(FirebaseID.postID,postID);
            data.put(FirebaseID.documentID,mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.nickname,nickname);
            data.put(FirebaseID.title,mTitle.getText().toString());
            data.put(FirebaseID.contents,mContents.getText().toString());
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection("marketpost").document(postID).set(data, SetOptions.merge());
            finish();
        }
    }
}
