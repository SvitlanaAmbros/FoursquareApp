package com.example.admin.foursquareapp.server;

import com.example.admin.foursquareapp.dto_place_detail.AdditionalInfoPlaceResponse;
import com.example.admin.foursquareapp.dto_place_info.PlaceInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Admin on 25.12.2017.
 */

public interface FourSquareAPI {
        @GET("v2/venues/search")
        Call<PlaceInfoResponse> getDataByQuery(@Query("client_id") String clientId,
                                               @Query("client_secret") String clientSecret,
                                               @Query("v") String v,
                                               @Query("ll") String ll,
                                               @Query("query") String query);

        @GET("v2/venues/{id}")
        Call<AdditionalInfoPlaceResponse> getAddInfoPlace(@Path("id") String id,
                                                          @Query("client_id") String clientId,
                                                          @Query("client_secret") String clientSecret,
                                                          @Query("v") String v);

}
