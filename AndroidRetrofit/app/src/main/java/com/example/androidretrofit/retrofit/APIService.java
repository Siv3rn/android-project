package com.example.androidretrofit.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {

    private static String BASE_URL ="https://demo.lazday.com/rest-api-sample/";
    private static Retrofit retrofit;
    public static APIEndpoint endpoint(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIEndpoint.class);

    }
}
