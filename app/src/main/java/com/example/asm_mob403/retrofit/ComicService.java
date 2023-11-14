package com.example.asm_mob403.retrofit;

import com.example.asm_mob403.model.Comic;
import com.example.asm_mob403.model.Comment;
import com.example.asm_mob403.model.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ComicService {
    @GET("Api/getAllComic")
    Call<List<Comic>> getComics();

    @POST("Api/post/comic")
    Call<Comic> createComic(@Body Comic comic);

    @PATCH("Api/edit/comic/{id}")
    Call<Comic> updateComic(@Path("id")String id, @Body Comic comic);

    @DELETE("Api/delete/comic/{id}")
    Call<Comic> deleteComic(@Path("id")String id);

    @GET("Api/getDetailComic/{id}")
    Call<Comic> getDetailComic(@Path("id") String id);

    @GET("Api/readComic/{id}")
    Call<List<String>> readComic(@Path("id") String id);


    @POST("Api/login/user")
    Call<User> login(@Body User user);

    @POST("Api/signup/user")
    Call<User> signUp(@Body User user);

    @GET("Api/getAllComment/{id}")
    Call<List<Comment>> getComment(@Path("id") String id);

    @POST("Api/post/comment")
    Call<Comment> postComment(@Body Comment comment);

}
