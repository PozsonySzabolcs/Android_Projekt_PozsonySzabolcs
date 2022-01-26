package com.example.retrofitplayers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    RecyclerView rvPlayers;
    PlayersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        Call<List<Player>> call = service.getAllPlayers();
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                rvPlayers = findViewById(R.id.rvPlayers);
                adapter = new PlayersAdapter(ListActivity.this, response.body());
                rvPlayers.setLayoutManager(new LinearLayoutManager(ListActivity.this));
                rvPlayers.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Toast.makeText(ListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}