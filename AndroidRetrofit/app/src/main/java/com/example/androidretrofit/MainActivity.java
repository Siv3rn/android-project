package com.example.androidretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidretrofit.retrofit.APIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainAdapter mainAdapter;
    private List<MainModel.Result> results =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        setupRecyclerView();
        getDataFromApi();
    }

    private void setupRecyclerView() {
        mainAdapter = new MainAdapter(results, new MainAdapter.OnAdapterListener() {
            @Override
            public void onClick(MainModel.Result result) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("intentTitle", result.getTitle());
                intent.putExtra("intentImage", result.getImage());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyler_view);
        progressBar  = findViewById(R.id.progress_bar);
    }

    private void getDataFromApi(){
        progressBar.setVisibility(View.VISIBLE);

        APIService.endpoint().getData()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        progressBar.setVisibility(View.GONE);
                        if(response.isSuccessful()){
                            List<MainModel.Result> results = response.body().getResult();
                            Log.d(TAG, results.toString());
                            mainAdapter.setData(results);

                        }
                    }

                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, t.toString());

                    }
                });
    }
}