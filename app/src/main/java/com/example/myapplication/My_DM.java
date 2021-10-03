package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.commentadapter;
import com.example.myapplication.adapters.postadapter;
import com.example.myapplication.model.comment;
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

public class My_DM extends AppCompatActivity {

    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();
    private List<comment> cDatas;
    private commentadapter cAdapter;
    private RecyclerView DMRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dm);

        DMRecyclerView = findViewById(R.id.DM_recyclerview);

    }

    @Override
    protected void onStart() {
        super.onStart();
        cDatas=new ArrayList<>();
        mStore.collection(FirebaseID.user)//파이어스토어에서 post로 되어있는 collectionreference 만들기
                .document(mAuth.getCurrentUser().getUid())
                .collection("dm")//시간순서대로 오름차순
                .orderBy(FirebaseID.timestamp, Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value!=null){
                            cDatas.clear();
                            for(DocumentSnapshot snap: value.getDocuments()){//for-each문 value.getDocument()에 저장된 값을 DocumentSnapshot 형식의 snap을 통해 가져옴
                                Map<String,Object> shot=snap.getData();//key는 string ,value는 object인 맵함수, value.getDocument의 정보 불러옴
                                String nickname=String.valueOf(shot.get((FirebaseID.nickname)));
                                String contents=String.valueOf(shot.get("DM_contents"));
                                comment data = new comment(nickname,contents);
                                cDatas.add(data);
                            }
                            cAdapter=new commentadapter(cDatas);//mdatas를 madapter에 저장
                            DMRecyclerView.setAdapter(cAdapter);
                        }
                    }
                });

    }

}
