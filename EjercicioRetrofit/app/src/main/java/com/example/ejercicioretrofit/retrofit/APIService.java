package com.example.ejercicioretrofit.retrofit;

import com.example.ejercicioretrofit.models.Comment;
import com.example.ejercicioretrofit.models.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface APIService {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{postId}/comments")
    Call<List<Comment>> getCommentsFromPost(@Path("postId") int postId);
}
