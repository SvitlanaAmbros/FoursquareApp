package com.example.admin.foresquareapp.server;

import com.example.admin.foresquareapp.dto_place_detail.ResponceAddedInfoPlace;
import com.example.admin.foresquareapp.dto_place_info.ResponseByQuery;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 25.12.2017.
 */

public interface FourSquareAPI {
        @GET("v2/venues/search")
        Call<ResponseByQuery> getDataByQuery(@Query("client_id") String clientId,
                                             @Query("client_secret") String clientSecret,
                                             @Query("v") String v,
                                             @Query("ll") String ll,
                                             @Query("query") String query);

        @GET("v2/venues/{id}")
        Call<ResponceAddedInfoPlace> getAddInfoPlace(@Path("id") String id,
                                                     @Query("client_id") String clientId,
                                                     @Query("client_secret") String clientSecret,
                                                     @Query("v") String v);

}
