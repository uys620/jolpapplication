package com.example.myapplication.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.FirebaseID;
import com.example.myapplication.Post2Activity;
import com.example.myapplication.R;
import com.example.myapplication.model.post;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class postadapter extends  RecyclerView.Adapter<postadapter.PostViewHolder> {//리사이클러뷰에서 어댑터는 recyclerview.adapter<사용할 뷰홀더 지정>를 상속해서 구현해야함

    private List<post> datas;

    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();


    public postadapter(List<post> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//3가지 메서드 onCreateViewholder 뷰홀더를 생성(레이아웃 생성)
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false));//layoutinflator: 특정 xml파일을 클래스로 변환하게 도와줌
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {//onBindviewholder 뷰홀더가 재활용될때 실행되는 메서드
        post data=datas.get(position);
        holder.nickname.setText("작성자:"+data.getNickname());
        holder.title.setText(data.getTitle());
        holder.contents.setText(data.getContents());
        holder.postID.setText(data.getPostID());



    }

    @Override
    public int getItemCount() {//getitemcount 아이템 개수를 조회
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {//사용할 뷰홀더

        private TextView nickname;
        private TextView title;
        private TextView contents;
        private TextView postID;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            nickname=itemView.findViewById(R.id.item_post_nickname);
            title=itemView.findViewById(R.id.item_post_title);
            contents=itemView.findViewById(R.id.item_post_contents);
            postID=itemView.findViewById(R.id.item_post_id);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION)
                    {
                        post Post = datas.get(pos);
                        Intent intent = new Intent(v.getContext(), Post2Activity.class);

                        intent.putExtra("nickname",datas.get(pos).getNickname());
                        intent.putExtra("title",datas.get(pos).getTitle());
                        intent.putExtra("contents",datas.get(pos).getContents());
                        intent.putExtra("postID",datas.get(pos).getPostID());
                        intent.putExtra("documentId",datas.get(pos).getDocumentId());


                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }


}
