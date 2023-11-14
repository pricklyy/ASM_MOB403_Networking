package com.example.asm_mob403.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob403.R;
import com.example.asm_mob403.activity.DetailActivity;
import com.example.asm_mob403.model.Comment;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;

import java.io.Serializable;
import java.util.List;

public class    CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private Context context;
     List<Comment> commentList;
     ComicService comicService;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
        comicService = RetrofitInstance.getApiService();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_item_comment,parent,false);
        return new ViewHolder(view);

    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.tvUser.setText(commentList.get(position).getUserId());
        holder.tvContent.setText(commentList.get(position).getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("comment", (Serializable) commentList.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUser,tvContent;
        ImageView imgUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvUser = itemView.findViewById(R.id.tvUser);
            imgUser = itemView.findViewById(R.id.imgUser);
        }
    }
}
