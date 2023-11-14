package com.example.asm_mob403.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob403.R;
import com.example.asm_mob403.adapter.CommentAdapter;
import com.example.asm_mob403.model.Comic;
import com.example.asm_mob403.model.Comment;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private Button btnDoc,btnComment,btnBinhLuan;
    private ComicService comicService;

//    private CommentService commentService;
    private TextView tvTitle,tvAuthor,tvDescription;
    private ImageView imgDetail;
    private Comic comic;
    private Comment comment;
    private List<Comic> list = new ArrayList<>();

    private List<Comment> commentList = new ArrayList<>();
    private RecyclerView recyclerViewListComment;

    private CommentAdapter adapter;

    private EditText edtComment,edtComicId,edtUserId,edtContent,edtDate;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnDoc = findViewById(R.id.btnRead);
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvTacgia);
        tvDescription = findViewById(R.id.tvMota);
        imgDetail = findViewById(R.id.imgDetail);
        btnComment = findViewById(R.id.btnComment);

         edtComicId = findViewById(R.id.edtComicId);
         edtUserId = findViewById(R.id.edtIdUser);
         edtContent = findViewById(R.id.edtContent);
         edtDate = findViewById(R.id.edtDate);
         btnBinhLuan = findViewById(R.id.btnBinhLuan);

        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ChapterActivity.class);
                intent.putExtra("comicId","");
                startActivity(intent);
            }
        });

        getDetail();
//        String comicId = "655a1fe20b8c7a4e378a61a8";
            getComment();
        postComment();

    }


    private void getDetail() {
        comic = (Comic) getIntent().getSerializableExtra("comic");
        comicService = RetrofitInstance.getApiService();
        Call<Comic> call = comicService.getDetailComic(comic.get_id().toString());
        call.enqueue(new Callback<Comic>() {
            @Override
            public void onResponse(Call<Comic> call, Response<Comic> response) {
                if(response.isSuccessful()) {
                    Comic comic1 = response.body();
                    tvTitle.setText(comic1.getTitle());
                    tvDescription.setText(comic1.getDescription());
                    tvAuthor.setText(comic1.getAuthor());
                    Picasso.get().load(comic1.getCoverImage()).into(imgDetail);
                }
            }

            @Override
            public void onFailure(Call<Comic> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void getComment() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.VERTICAL,false);
        recyclerViewListComment = findViewById(R.id.listComment);
        recyclerViewListComment.setLayoutManager(layoutManager);
        comicService = RetrofitInstance.getApiService();


//        String comicId = comment.getComicId();
//        Log.e("id","id"+comment.getComicId(),null);


//        String comicId = getIntent().getStringExtra("comment");
        comic = (Comic) getIntent().getSerializableExtra("comic");
        Call<List<Comment>> call = comicService.getComment(comic.get_id().toString());
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(response.isSuccessful()) {
                    commentList = response.body();
                    adapter = new CommentAdapter(DetailActivity.this,commentList);
                    recyclerViewListComment.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void postComment() {
           btnComment.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
                   startActivity(intent);
               }
           });
    }
}