package com.example.asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob403.R;
import com.example.asm_mob403.model.Comment;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends AppCompatActivity {

    private EditText edtComicId,edtUserId,edtContent,edtDate;
    private Button btnBinhLuan;

    private ComicService comicService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        edtComicId = findViewById(R.id.edtComicId);
        edtUserId = findViewById(R.id.edtIdUser);
        edtContent  = findViewById(R.id.edtContent);
        edtDate = findViewById(R.id.edtDate);
        btnBinhLuan = findViewById(R.id.btnBinhLuan);
        comicService = RetrofitInstance.getApiService();

        btnBinhLuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comicId = edtComicId.getText().toString();
                String userId = edtUserId.getText().toString();
                String content = edtContent.getText().toString();
                String date = edtDate.getText().toString();

                Comment comment = new Comment(comicId,userId,content,date);
                Call<Comment> call = comicService.postComment(comment);
                call.enqueue(new Callback<Comment>() {
                    @Override
                    public void onResponse(Call<Comment> call, Response<Comment> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(CommentActivity.this, "Cảm ơn bạn đã bình luận và góp ý!", Toast.LENGTH_SHORT).show();
                            recreate();

                        }
                    }

                    @Override
                    public void onFailure(Call<Comment> call, Throwable t) {
                        Toast.makeText(CommentActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}