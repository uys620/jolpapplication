package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FrontPageActivity extends AppCompatActivity {

    Button my_post, my_reply, free_board, market_board, my_dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

        my_post = findViewById(R.id.my_post);
        my_reply = findViewById(R.id.my_reply);
        free_board = findViewById(R.id.free_board);
        market_board = findViewById(R.id.market_board);
        my_dm = findViewById(R.id.my_dm);

        findViewById(R.id.main_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),FrontPageActivity.class);
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

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v){ // 버튼을 클릭해서 각 id에 따라서 이동을 해준다.
                switch(v.getId()){
                    case R.id.my_post:
                        startActivity(new Intent(getApplicationContext(),My_Post.class));
                        break;
                    case R.id.my_reply:
                        startActivity(new Intent(getApplicationContext(),My_Reply.class));
                        break;
                    case R.id.free_board:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        break;
                    case R.id.market_board:
                        startActivity(new Intent(getApplicationContext(),Market_board.class));
                        break;
                    case R.id.my_dm:
                        startActivity(new Intent(getApplicationContext(),My_DM.class));
                        break;
                }
            }
        };

        my_post.setOnClickListener(onClickListener);
        my_reply.setOnClickListener(onClickListener);
        free_board.setOnClickListener(onClickListener);
        market_board.setOnClickListener(onClickListener);
        my_dm.setOnClickListener(onClickListener);
    }
}