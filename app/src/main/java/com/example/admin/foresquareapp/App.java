package com.example.admin.foresquareapp;

import android.app.Application;

import com.example.admin.foresquareapp.model.MainModel;
import com.example.admin.foresquareapp.model.PlacesModel;

/**
 * Created by Admin on 25.12.2017.
 */

public class App extends Application {
    private static App instance;
    private MainModel mainModel;

    public static final String FOURSQUARE_CLIENT_KEY = "P0YETFTNSXPCFHSDMTREAOT2HEOXVIBMSGZKS13MGTPJ4MRE";
    public static final String FOURSQUARE_CLIENT_SECRET = "Z0B44UL12JHTLVJR5G3LIN5WFMCA5DRROOYOM3C1TXWXIG4S";

    public final static String BROADCAST_ACTION = "ru.networkreceiver.networkconnection";
    private String typeInternetConnection;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mainModel = new PlacesModel();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;

    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public void setMainModel(MainModel mainModel) {
        this.mainModel = mainModel;
    }

    public static App getInstance() {
        return instance;
    }

    public String getTypeInternetConnection() {
        return typeInternetConnection;
    }

    public void setTypeInternetConnection(String typeInternetConnection) {
        this.typeInternetConnection = typeInternetConnection;
    }
}
