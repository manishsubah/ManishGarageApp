package com.example.manishgarageapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface firstApi {

    @GET("getallmakes?format=json")
    Call<JsonArray> getCourse();
}
