package com.example.manishgarageapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface secondApi {

    @GET("0?format=json")
    Call<JsonArrayModel> getModel();
}
