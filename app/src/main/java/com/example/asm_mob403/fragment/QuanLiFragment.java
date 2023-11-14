package com.example.asm_mob403.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.R;
import com.example.asm_mob403.retrofit.RetrofitInstance;
import com.example.asm_mob403.activity.ListComicScreen;
import com.example.asm_mob403.model.Comic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class QuanLiFragment extends Fragment {

    ArrayAdapter<Comic> adapter;
    ComicService comicService;
     EditText edtTitle,edtDescription,edtAuthor,edtYear,edtCoverImage,edtImages;
    Button btnThem , btnXem;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    public QuanLiFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static QuanLiFragment newInstance() {
        QuanLiFragment fragment = new QuanLiFragment();
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
        return inflater.inflate(R.layout.fragment_quan_li, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
            edtTitle = view.findViewById(R.id.edtTitle);
        edtDescription = view.findViewById(R.id.edtDescription);
        edtAuthor = view.findViewById(R.id.edtAuthor);
        edtYear = view.findViewById(R.id.edtYear);
        edtCoverImage = view.findViewById(R.id.edtYear);
        edtImages = view.findViewById(R.id.edtImages);
        comicService = RetrofitInstance.getApiService();
        btnThem = view.findViewById(R.id.btnThem);
        btnXem = view.findViewById(R.id.btnXem);


        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListComicScreen.class);
                intent.putExtra("key", "value"); // Thay "key" và "value" bằng dữ liệu bạn muốn truyền
                startActivity(intent);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String description = edtDescription.getText().toString();
                String author = edtAuthor.getText().toString();
                int year = Integer.parseInt(edtYear.getText().toString());
                String coverimage = edtCoverImage.getText().toString();
//                String images = edtImages.getText().toString();
                Comic micoc = new Comic(title,description,author,year,coverimage);
                Call<Comic> call = comicService.createComic(micoc);
                call.enqueue(new Callback<Comic>() {
                    @Override
                    public void onResponse(Call<Comic> call, Response<Comic> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(getActivity(),"thành công",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(),"ko thành công",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Comic> call, Throwable t) {
                        Toast.makeText(getActivity(),"Lỗi!!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}