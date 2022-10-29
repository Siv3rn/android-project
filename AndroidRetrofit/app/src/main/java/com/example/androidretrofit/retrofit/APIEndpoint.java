package com.example.androidretrofit.retrofit;

import com.example.androidretrofit.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIEndpoint {

    @GET("data.php")
    Call<MainModel> getData();

}
