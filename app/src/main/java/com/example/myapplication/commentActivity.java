package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.myapplication.FirebaseID.postID;

public class commentActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private String nickname;
    private EditText mComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        findViewById(R.id.comment_send).setOnClickListener(this);
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

            mComment = findViewById(R.id.comment_message);
            //String postID=mStore.collection(FirebaseID.post).document().getId();
            String postID=getIntent().getExtras().getString("postID");

            String commentID=mStore.collection(FirebaseID.post).document(postID).collection(FirebaseID.comment).document().getId();
            Map<String,Object> data=new HashMap<>();
            data.put(FirebaseID.documentID,mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.nickname,nickname);
            data.put(FirebaseID.comment_contents,mComment.getText().toString());//안들어감 넣어야함
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(FirebaseID.post).document(postID).collection(FirebaseID.comment).document(commentID).set(data,SetOptions.merge());
            //mStore.collection(FirebaseID.post).document("postID").set(data, SetOptions.merge());

            String commentID2=mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid()).collection(FirebaseID.comment).document().getId();
            Map<String,Object> data2=new HashMap<>();
            data.put(FirebaseID.postID,postID);
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid()).collection(FirebaseID.comment).document(commentID2).set(data2,SetOptions.merge());
            finish();

        }
    }
}