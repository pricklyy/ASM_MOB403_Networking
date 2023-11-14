package com.example.asm_mob403.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_mob403.R;
import com.example.asm_mob403.activity.ChapterActivity;
import com.example.asm_mob403.activity.DetailActivity;
import com.example.asm_mob403.model.Comic;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyComicAdapter extends RecyclerView.Adapter<MyComicAdapter.MyComicViewHolder> {
    Context context;
    List<Comic> comicList;

    private ComicService comicService;
    public MyComicAdapter(Context context, List<Comic> comicList) {
        this.context = context;
        this.comicList = comicList;
        comicService = RetrofitInstance.getApiService();
    }

    @NonNull
    @Override
    public MyComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.list_comic_item,parent,false);
        return new MyComicViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyComicViewHolder holder, int position) {
        Comic comic = comicList.get(position);
        Picasso.get().load(comicList.get(position).getCoverImage()).into(holder.imageView);
        holder.textView.setText(comicList.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("comic", (Serializable) comicList.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    public class MyComicViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public MyComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.comic_title);
        }
    }
}
