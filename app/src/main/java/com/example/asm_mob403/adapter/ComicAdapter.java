package com.example.asm_mob403.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.R;
import com.example.asm_mob403.retrofit.RetrofitInstance;
import com.example.asm_mob403.model.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder>{


    private List<Comic> list;
    private Context mContext;
    private ComicService comicService;

    public ComicAdapter(List<Comic> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        comicService = RetrofitInstance.getApiService();
    }


    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.layout_item_comic,parent,false);

        return new ComicViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comic comic = list.get(position);
        holder.tvTitle.setText(comic.getTitle());
        holder.tvDescription.setText(comic.getDescription());
        holder.tvAuthor.setText(comic.getAuthor());
        holder.tvYear.setText(""+comic.getYear());
        Picasso.get().load(comic.getCoverImage()).into(holder.imgAvata);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delete");
                builder.setMessage("Do you want to del");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<Comic>call = comicService.deleteComic(comic.get_id().toString());
                        call.enqueue(new Callback<Comic>() {
                            @Override
                            public void onResponse(Call<Comic> call, Response<Comic> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(mContext,"Xóa thành công",Toast.LENGTH_SHORT).show();
                                    list.remove(comic);
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(mContext,"Xóa không thành công",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Comic> call, Throwable t) {
                                Toast.makeText(mContext,"Bug",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                AlertDialog dialog = builder.create();
                View view1 = View.inflate(mContext,R.layout.edit_comic,null);
                dialog.setView(view1);
                EditText edtTitle = view1.findViewById(R.id.edttitle);
                EditText edtdescription = view1.findViewById(R.id.edtdescription);
                EditText edtAuthor = view1.findViewById(R.id.edtauthor);
                EditText edtYear = view1.findViewById(R.id.edtyear);
                EditText edtCoverImage = view1.findViewById(R.id.edtcoverimage);
                EditText edtImages = view1.findViewById(R.id.edtimages);
                edtTitle.setText(comic.getTitle());
                edtdescription.setText(comic.getDescription());
                edtAuthor.setText(comic.getAuthor());
                edtYear.setText(""+comic.getYear());
                edtCoverImage.setText(comic.getCoverImage());
                Button btnUpdate =view1.findViewById(R.id.btnUpdate);

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Comic comic1 = new Comic(edtTitle.getText().toString(),
                                edtdescription.getText().toString(),
                                edtAuthor.getText().toString(),
                                Integer.parseInt(edtYear.getText().toString()),
                                edtCoverImage.getText().toString());

                        Call<Comic> call = comicService.updateComic(comic.get_id().toString(),comic1);
                        call.enqueue(new Callback<Comic>() {
                            @Override
                            public void onResponse(Call<Comic> call, Response<Comic> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(mContext,"Update thành công",Toast.LENGTH_SHORT).show();
                                    list.set(position,comic1);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(mContext,"Update thất bại",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<Comic> call, Throwable t) {
                                Toast.makeText(mContext," Bug",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ComicViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgAvata, imgDelete, imgEdit;
        private TextView tvTitle,tvDescription,tvAuthor,tvYear;
        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvata = itemView.findViewById(R.id.imgAvata);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvYear = itemView.findViewById(R.id.tvYear);
        }
    }
}
