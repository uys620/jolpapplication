package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapters.commentadapter;
import com.example.myapplication.adapters.postadapter;
import com.example.myapplication.model.comment;
import com.example.myapplication.model.post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import static com.example.myapplication.FirebaseID.documentID;
import static com.example.myapplication.FirebaseID.postID;

public class Post2Activity extends AppCompatActivity {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private String Title;
    private String Contents;
    private String nickname;
    private String postID;
    private String documentId;

    private TextView mTitle;
    private TextView mContents;
    private TextView mNickname;
    private TextView mPostid;
    private RecyclerView CommentRecyclerView;
    private List<comment> cDatas;
    private commentadapter cAdapter;
    private Button edit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post2);
        CommentRecyclerView = findViewById(R.id.comment_view);
        edit_button = findViewById(R.id.edit_button);

        edit_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(mAuth.getCurrentUser().getUid().equals(documentId)){
                    my_post();
                }
                else{
                    other_post();
                }
            }

            void my_post(){

                final CharSequence[] items = {"??????", "??????" , "??????"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Post2Activity.this);
                builder.setTitle("????????????");
                builder.setItems(items, new DialogInterface.OnClickListener()  {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(),PostEditActivity.class);
                                intent.putExtra("contents",Contents);
                                intent.putExtra("postId",postID);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),items[which] + "??????", Toast.LENGTH_SHORT).show();
                                break;

                            case 1:
                                mStore.collection(FirebaseID.post).document(postID)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d("????????????", "DocumentSnapshot successfully deleted!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("????????????", "Error deleting document", e);
                                            }
                                        });
                                Toast.makeText(getApplicationContext(),items[which] + "??????", Toast.LENGTH_SHORT).show();
                                break;

                            case 2:
                                Toast.makeText(getApplicationContext(),items[which] + "??????", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            void other_post(){

                final CharSequence[] items = {"?????? ?????????", "??????" , "??????"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Post2Activity.this);

                builder.setTitle("????????????");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(),dmActivity.class);
                                intent.putExtra("documentId",documentId);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),items[which] + "??????", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(getApplicationContext(),items[which] + "??????", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(),items[which] + "??????", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });



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

        Intent intent = getIntent();

        Title = intent.getExtras().getString("title");
        Contents = intent.getExtras().getString("contents");
        nickname = intent.getExtras().getString("nickname");
        postID= intent.getExtras().getString("postID");
        documentId = intent.getExtras().getString("documentId");

        mTitle.setText(Title);
        mContents.setText(Contents);
        mNickname.setText("?????????:"+nickname);

        cDatas=new ArrayList<>();
        mStore.collection(FirebaseID.post)//???????????????????????? post??? ???????????? collectionreference ?????????
                .document(postID)
                .collection(FirebaseID.comment)//?????????????????? ????????????
                .orderBy(FirebaseID.timestamp, Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value!=null){
                            cDatas.clear();
                            for(DocumentSnapshot snap: value.getDocuments()){//for-each??? value.getDocument()??? ????????? ?????? DocumentSnapshot ????????? snap??? ?????? ?????????
                                Map<String,Object> shot=snap.getData();//key??? string ,value??? object??? ?????????, value.getDocument??? ?????? ?????????
                                String nickname=String.valueOf(shot.get((FirebaseID.nickname)));
                                String contents=String.valueOf(shot.get(FirebaseID.comment_contents));
                                comment data = new comment(nickname,contents);
                                cDatas.add(data);
                            }
                            cAdapter=new commentadapter(cDatas);//mdatas??? madapter??? ??????
                            CommentRecyclerView.setAdapter(cAdapter);
                        }
                    }
                });
    }


}