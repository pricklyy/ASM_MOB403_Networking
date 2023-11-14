package com.example.asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.R;
import com.example.asm_mob403.retrofit.RetrofitInstance;
import com.example.asm_mob403.adapter.ComicAdapter;
import com.example.asm_mob403.model.Comic;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListComicScreen extends AppCompatActivity {
    private RecyclerView recyclerViewComic;
    private ComicService comicService;
    private List<Comic> comicList = new ArrayList<>();
    private ComicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_comic_screen);

        recyclerViewComic= findViewById(R.id.recycleViewListComic);
        recyclerViewComic.setLayoutManager(new LinearLayoutManager(this));


        comicService = RetrofitInstance.getApiService();
        Call<List<Comic>> call = comicService.getComics();
        call.enqueue(new Callback<List<Comic>>() {
            @Override
            public void onResponse(Call<List<Comic>> call, Response<List<Comic>> response) {
                    if(response.isSuccessful()) {
                        comicList = response.body();
                        adapter = new ComicAdapter(comicList,ListComicScreen.this);
                        recyclerViewComic.setAdapter(adapter);
                    }
            }

            @Override
            public void onFailure(Call<List<Comic>> call, Throwable t) {
                Toast.makeText(ListComicScreen.this,"Lỗi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}