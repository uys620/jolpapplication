package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.Map;

import javax.annotation.Nonnull;

public class Post2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private String Title;
    private String Contents;
    private String nickname;

    private TextView mTitle;
    private TextView mContents;
    private TextView mNickname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);

        mTitle=findViewById(R.id.post2_title);
        mContents=findViewById(R.id.post2_content);
        mNickname=findViewById(R.id.post2_name);



        Intent intent = getIntent();

        Title = intent.getExtras().getString("title");
        Contents = intent.getExtras().getString("contents");
        nickname = intent.getExtras().getString("nickname");

        mTitle.setText(Title);
        mContents.setText(Contents);
        mNickname.setText(nickname);
    }
}