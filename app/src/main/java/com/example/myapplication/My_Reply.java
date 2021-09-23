package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapters.postadapter;
import com.example.myapplication.model.post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class My_Reply extends AppCompatActivity {

    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();

    private RecyclerView mPostRecyclerView;

    private postadapter mAdapter;
    private List<post> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__reply);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas=new ArrayList<>();
        mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid()).collection(FirebaseID.comment)//파이어스토어에서 post로 되어있는 collectionreference 만들기
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value!=null){
                            mDatas.clear();
                            for(DocumentSnapshot snap: value.getDocuments()){//for-each문 value.getDocument()에 저장된 값을 DocumentSnapshot 형식의 snap을 통해 가져옴
                                Map<String,Object> shot=snap.getData();//key는 string ,value는 object인 맵함수, value.getDocument의 정보 불러옴
                                String PostID=String.valueOf(shot.get(FirebaseID.postID));//key:String에 해당하는 Object를 가져와서 스트링으로 바꿔서 사용

                                DocumentReference docRef = mStore.collection(FirebaseID.post).document(PostID);

                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                String documentID=String.valueOf(document.get(FirebaseID.documentID));//key:String에 해당하는 Object를 가져와서 스트링으로 바꿔서 사용
                                                String nickname=String.valueOf(document.get((FirebaseID.nickname)));
                                                String title= String.valueOf(document.get(FirebaseID.title));
                                                String contents=String.valueOf(document.get(FirebaseID.contents));
                                                post data = new post(documentID, nickname, title, contents);
                                                mDatas.add(data);
                                            } else {

                                            }
                                        } else {
                                            //Log.d(TAG, "get failed with ", task.getException());
                                        }
                                    }
                                });

                            }
                            mAdapter=new postadapter(mDatas);//mdatas를 madapter에 저장
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }
}