package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth=FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();

    private EditText eMailText;
    private EditText mPasswordText;
    private EditText mNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mNickname=findViewById(R.id.sign_nickname);
        eMailText=findViewById(R.id.email);
        mPasswordText=findViewById(R.id.pass);



        findViewById(R.id.sign).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        mAuth.createUserWithEmailAndPassword(eMailText.getText().toString(), mPasswordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           FirebaseUser user = mAuth.getCurrentUser();
                           if(user!=null){
                            Map<String,Object> userMap=new HashMap<>();
                            userMap.put(FirebaseID.documentID,user.getUid());
                            userMap.put(FirebaseID.nickname,mNickname.getText().toString());
                            userMap.put(FirebaseID.email,eMailText.getText().toString());
                            userMap.put(FirebaseID.password,mPasswordText.getText().toString());
                            mStore.collection(FirebaseID.user).document(user.getUid()).set(userMap, SetOptions.merge());
                            finish();
                            }
                        } else {
                            Toast.makeText(signupActivity.this, "Sign up error.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}