package com.example.turbocareassignment.model.Connectivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("makes")
    Call<List<String>> twoAndFourWheelerApi(
            @Query("make") String make);

    @GET("models")
    Call<List<String>> LIST_CALL(
            @Query("class") String classValue,
            @Query("make") String make);

}
