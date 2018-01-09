package com.example.admin.foursquareapp.server;

import com.example.admin.foursquareapp.App;
import com.example.admin.foursquareapp.dto_place_detail.AdditionalInfoPlaceResponse;
import com.example.admin.foursquareapp.dto_place_info.PlaceInfoResponse;
import com.example.admin.foursquareapp.interfaces.AddInfoPlaceLoaderListener;
import com.example.admin.foursquareapp.interfaces.MainInfoPlaceLoadListener;
import com.example.admin.foursquareapp.interfaces.ServerResponseListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 25.12.2017.
 */

public class ServerRequestBuilder {
    private Retrofit retrofit;
    private static FourSquareAPI fourSquareAPI;
    private ServerResponseListener serverResponseListener;
    private MainInfoPlaceLoadListener mainInfoPlaceLoadListener;
    private AddInfoPlaceLoaderListener addInfoPlaceLoaderListener;

    public ServerRequestBuilder(ServerResponseListener serverResponseListener, MainInfoPlaceLoadListener mainInfoPlaceLoadListener,
                                AddInfoPlaceLoaderListener addInfoPlaceLoaderListener) {
        this.serverResponseListener = serverResponseListener;
        this.mainInfoPlaceLoadListener = mainInfoPlaceLoadListener;
        this.addInfoPlaceLoaderListener = addInfoPlaceLoaderListener;
    }

    public void createConnection(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.foursquare.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fourSquareAPI = retrofit.create(FourSquareAPI.class);
    }

    public void searchByQuery(String coord, String query){
        createConnection();

        Call<PlaceInfoResponse> call = fourSquareAPI.getDataByQuery(App.FOURSQUARE_CLIENT_KEY,
                App.FOURSQUARE_CLIENT_SECRET, getCurrentDataInfo(), coord, query);

        call.enqueue(new Callback<PlaceInfoResponse>() {

            @Override
            public void onResponse(Call<PlaceInfoResponse> call, Response<PlaceInfoResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mainInfoPlaceLoadListener.infoLoaded(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<PlaceInfoResponse> call, Throwable t) {
                serverResponseListener.errorRequest();
            }
        });
    }

    public void searchAddInfoPlace(String id){
        Call<AdditionalInfoPlaceResponse> callInfo = fourSquareAPI.getAddInfoPlace(id,
                App.FOURSQUARE_CLIENT_KEY,
                App.FOURSQUARE_CLIENT_SECRET,
                getCurrentDataInfo());

        callInfo.enqueue(new Callback<AdditionalInfoPlaceResponse>() {

            @Override
            public void onResponse(Call<AdditionalInfoPlaceResponse> call, Response<AdditionalInfoPlaceResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        addInfoPlaceLoaderListener.additionalInfoLoaded(response.body());
//                        serverResponseListener.successfulRequest(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<AdditionalInfoPlaceResponse> call, Throwable t) {
                serverResponseListener.errorRequest();
            }
        });
    }

    public String getCurrentDataInfo(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }
}
