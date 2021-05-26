package com.example.turbocareassignment.model.Connectivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    /**
     * Based on 2w and 4w get Companies name's
     *
     * @param make
     * @return
     */
    @GET("makes")
    Call<List<String>> twoAndFourWheelerApi(
            @Query("make") String make);

    /**
     * Based on vehicle type and company name get the model name's
     *
     * @param classValue
     * @param make
     * @return
     */
    @GET("models")
    Call<List<String>> LIST_CALL(
            @Query("class") String classValue,
            @Query("make") String make);

}
