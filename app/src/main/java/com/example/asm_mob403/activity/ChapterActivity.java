package com.example.asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asm_mob403.R;
import com.example.asm_mob403.adapter.DocTruyenAdapter;
import com.example.asm_mob403.adapter.MyComicAdapter;
import com.example.asm_mob403.model.Comic;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChapterActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRead;
    private ComicService comicService;
    private List<String> list = new ArrayList<>();
    private DocTruyenAdapter adapter;


    Comic comic;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);



        readComic();


    }

    private void readComic() {
        recyclerViewRead = findViewById(R.id.recycleViewReadComic);
        recyclerViewRead.setLayoutManager(new LinearLayoutManager(this));

        comicService = RetrofitInstance.getApiService();
        Call<List<String>> call = comicService.readComic("655a1fe20b8c7a4e378a61a8");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()) {
                    list = response.body();
                    adapter = new DocTruyenAdapter(ChapterActivity.this,list);
                    recyclerViewRead.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(ChapterActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
