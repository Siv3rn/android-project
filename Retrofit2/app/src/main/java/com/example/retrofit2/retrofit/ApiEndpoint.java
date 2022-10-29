package com.example.retrofit2.retrofit;

import com.example.retrofit2.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("users/list?sort=desc")
    Call<MainModel> getData();


}
