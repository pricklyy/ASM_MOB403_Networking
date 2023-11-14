package com.example.asm_mob403.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asm_mob403.R;
import com.example.asm_mob403.model.User;
import com.example.asm_mob403.retrofit.ComicService;
import com.example.asm_mob403.retrofit.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ComicService comicService;
    EditText edtFullname,edtEmail,edtPassword,edtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView imgSignUp = findViewById(R.id.imgSignUp);
         edtUserName = findViewById(R.id.edtUser);
         edtPassword = findViewById(R.id.edtPass);
         edtEmail = findViewById(R.id.edtEmail);
         edtFullname = findViewById(R.id.edtFullName);



        imgSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String email = edtEmail.getText().toString();
                String fullname = edtFullname.getText().toString();
                comicService = RetrofitInstance.getApiService();
                User user1 = new User(username,password,email,fullname);
                Call<User> call = comicService.signUp(user1);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            response.body();
                            Toast.makeText(SignUpActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(SignUpActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });
    }



}