package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.post;

import java.util.List;

public class postadapter extends  RecyclerView.Adapter<postadapter.PostViewHolder> {

    private List<post> datas;

    public postadapter(List<post> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        post data=datas.get(position);
        holder.title.setText(data.getTitle());
        holder.contents.setText(data.getContents());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView contents;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.item_post_title);
            contents=itemView.findViewById(R.id.item_post_contents);
        }
    }
}
