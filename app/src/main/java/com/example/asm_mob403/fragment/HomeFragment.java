package com.example.asm_mob403.fragment;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.asm_mob403.R;
import com.example.asm_mob403.activity.ListComicScreen;
import com.example.asm_mob403.adapter.ComicAdapter;
import com.example.asm_mob403.adapter.MyComicAdapter;
import com.example.asm_mob403.model.Comic;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewComic;
    private ComicService comicService;
    private List<Comic> comicList = new ArrayList<>();
    private MyComicAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageSlider imageSlider = view.findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels =  new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.comic1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.comic2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.comic3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.comic4, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewComic= view.findViewById(R.id.recycleViewListComic1);
        recyclerViewComic.setLayoutManager(layoutManager);
        comicService = RetrofitInstance.getApiService();
        Call<List<Comic>> call = comicService.getComics();
        call.enqueue(new Callback<List<Comic>>() {
            @Override
            public void onResponse(Call<List<Comic>> call, Response<List<Comic>> response) {
                if(response.isSuccessful()) {
                    comicList = response.body();
                    adapter = new MyComicAdapter(getContext(),comicList);
                    recyclerViewComic.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Comic>> call, Throwable t) {
                Toast.makeText(getActivity(),"Lỗi",Toast.LENGTH_SHORT).show();
            }
        });
    }
}