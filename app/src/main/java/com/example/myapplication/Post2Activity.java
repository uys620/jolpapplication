package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.adapters.commentadapter;
import com.example.myapplication.adapters.postadapter;
import com.example.myapplication.model.comment;
import com.example.myapplication.model.post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import static com.example.myapplication.FirebaseID.postID;

public class Post2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private String Title;
    private String Contents;
    private String nickname;
    private String postID;

    private TextView mTitle;
    private TextView mContents;
    private TextView mNickname;
    private TextView mPostid;
    private RecyclerView CommentRecyclerView;
    private List<comment> cDatas;
    private commentadapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);
        CommentRecyclerView = findViewById(R.id.comment_view);

        findViewById(R.id.comment).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),commentActivity.class);
                intent.putExtra("postID",getIntent().getExtras().getString("postID"));
                startActivity(intent);
            }
        });

        mTitle=findViewById(R.id.post2_title);
        mContents=findViewById(R.id.post2_content);
        mNickname=findViewById(R.id.post2_name);
        mPostid=findViewById(R.id.post2_postid);

        Intent intent = getIntent();

        Title = intent.getExtras().getString("title");
        Contents = intent.getExtras().getString("contents");
        nickname = intent.getExtras().getString("nickname");
        postID= intent.getExtras().getString("postID");

        mTitle.setText(Title);
        mContents.setText(Contents);
        mNickname.setText(nickname);
        mPostid.setText(postID);

        cDatas=new ArrayList<>();
        mStore.collection(FirebaseID.post)//파이어스토어에서 post로 되어있는 collectionreference 만들기
                .document(postID)
                .collection(FirebaseID.comment)//시간순서대로 오름차순
                .orderBy(FirebaseID.timestamp, Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value!=null){
                            cDatas.clear();
                            for(DocumentSnapshot snap: value.getDocuments()){//for-each문 value.getDocument()에 저장된 값을 DocumentSnapshot 형식의 snap을 통해 가져옴
                                Map<String,Object> shot=snap.getData();//key는 string ,value는 object인 맵함수, value.getDocument의 정보 불러옴
                                String nickname=String.valueOf(shot.get((FirebaseID.nickname)));
                                String contents=String.valueOf(shot.get(FirebaseID.comment_contents));
                                comment data = new comment(nickname,contents);
                                cDatas.add(data);
                            }
                            cAdapter=new commentadapter(cDatas);//mdatas를 madapter에 저장
                            CommentRecyclerView.setAdapter(cAdapter);
                        }
                    }
                });
    }
}