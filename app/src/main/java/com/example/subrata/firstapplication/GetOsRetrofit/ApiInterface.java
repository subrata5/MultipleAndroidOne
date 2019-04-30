package com.example.subrata.firstapplication.GetOsRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getos.php")
    Call<List<Model>> getOsInfo(@Query("item_type") String item_type);
}
