package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class PostEditActivity extends AppCompatActivity {

    private String postId;
    private EditText editContents;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private Button saveButton;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpost);
        Intent intent = getIntent();


        editContents = findViewById(R.id.post_edit_contents_edit);
        saveButton = findViewById(R.id.save_edit_post);


        editContents.setText(intent.getExtras().getString("contents"));
        postId = intent.getExtras().getString("postId");

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DocumentReference docRef = mStore.collection(FirebaseID.post).document(postId);

                docRef.update("contents", editContents.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("성공", "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("실패", "DocumentSnapshot successfully updated!");
                            }
                        });
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

}
