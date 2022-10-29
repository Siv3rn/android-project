package com.example.retrofit2.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService  {
    private static String BASE_URL = "";
    private static Retrofit retrofit;
    private static ApiEndpoint endpoint (){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        return retrofit.create(ApiEndpoint.class);
    }


}
