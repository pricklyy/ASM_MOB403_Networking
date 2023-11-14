package com.example.asm_mob403.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
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

public class DocTruyenAdapter extends RecyclerView.Adapter<DocTruyenAdapter.ViewHolder>{

    private Context context;
    private List<String> comicList ;
    private ComicService comicService;

    private Comic comic;

    public DocTruyenAdapter(Context context, List<String> comicList) {
        this.context = context;
        this.comicList = comicList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.layout_item_read_comic,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String comic1 = comicList.get(position);
        Picasso.get().load(comicList.get(position)).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ChapterActivity.class);
                intent.putExtra("comic", (Serializable) comicList.get(position));

            }
        });


    }

    @Override
    public int getItemCount() {
        return comicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView  = itemView.findViewById(R.id.imgRead);
        }
    }
}
