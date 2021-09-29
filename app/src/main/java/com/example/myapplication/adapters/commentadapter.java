package com.example.myapplication.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Post2Activity;
import com.example.myapplication.R;
import com.example.myapplication.model.comment;
import com.example.myapplication.model.post;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class commentadapter extends RecyclerView.Adapter<commentadapter.CommentViewHolder> {


    private List<comment> datas;

    private FirebaseFirestore mStore=FirebaseFirestore.getInstance();


    public commentadapter(List<comment> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public commentadapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//3가지 메서드 onCreateViewholder 뷰홀더를 생성(레이아웃 생성)
        return new commentadapter.CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment,parent,false));//layoutinflator: 특정 xml파일을 클래스로 변환하게 도와줌
    }

    @Override
    public void onBindViewHolder(@NonNull commentadapter.CommentViewHolder holder, int position) {//onBindviewholder 뷰홀더가 재활용될때 실행되는 메서드
        comment data=datas.get(position);
        holder.nickname.setText("작성자:"+data.getNickname());
        holder.contents.setText(data.getContents());
    }

    @Override
    public int getItemCount() {//getitemcount 아이템 개수를 조회
        return datas.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {//사용할 뷰홀더

        private TextView nickname;
        private TextView contents;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            nickname=itemView.findViewById(R.id.comment_nickname);
            contents=itemView.findViewById(R.id.comment_contents);

        }
    }

}
