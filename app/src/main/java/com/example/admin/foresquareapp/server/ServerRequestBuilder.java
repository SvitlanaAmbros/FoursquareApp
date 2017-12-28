package com.example.admin.foresquareapp.server;

import com.example.admin.foresquareapp.App;
import com.example.admin.foresquareapp.dto_place_detail.ResponceAddedInfoPlace;
import com.example.admin.foresquareapp.dto_place_info.ResponseByQuery;
import com.example.admin.foresquareapp.interfaces.AddInfoPlaceLoaderListener;
import com.example.admin.foresquareapp.interfaces.MainInfoPlaceLoadListener;
import com.example.admin.foresquareapp.interfaces.ServerResponseListener;

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

        Call<ResponseByQuery> call = fourSquareAPI.getDataByQuery(App.FOURSQUARE_CLIENT_KEY,
                App.FOURSQUARE_CLIENT_SECRET, getCurrentDataInfo(), coord, query);

        call.enqueue(new Callback<ResponseByQuery>() {

            @Override
            public void onResponse(Call<ResponseByQuery> call, Response<ResponseByQuery> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mainInfoPlaceLoadListener.infoLoaded(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseByQuery> call, Throwable t) {
                serverResponseListener.errorRequest();
            }
        });
    }

    public void searchAddInfoPlace(String id){
        Call<ResponceAddedInfoPlace> callInfo = fourSquareAPI.getAddInfoPlace(id,
                App.FOURSQUARE_CLIENT_KEY,
                App.FOURSQUARE_CLIENT_SECRET,
                getCurrentDataInfo());

        callInfo.enqueue(new Callback<ResponceAddedInfoPlace>() {

            @Override
            public void onResponse(Call<ResponceAddedInfoPlace> call, Response<ResponceAddedInfoPlace> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        addInfoPlaceLoaderListener.additionalInfoLoaded(response.body());
//                        serverResponseListener.successfulRequest(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponceAddedInfoPlace> call, Throwable t) {
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
