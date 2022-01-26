package com.example.retrofitplayers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {
    @GET("players")
    Call<List<Player>> getAllPlayers();

    @POST("players")
    Call<Player> createPlayer(@Body Player player);

    @PUT("players/{id}")
    Call<Player> updatePlayer(@Path("id") int id, @Body Player player);

    @DELETE("players/{id}")
    Call<Player> deletePlayer(@Path("id") int id);

    @GET("players/{id}")
    Call<Player> getPlayer(@Path("id") int id);
}
