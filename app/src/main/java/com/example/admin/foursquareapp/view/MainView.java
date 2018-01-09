package com.example.admin.foursquareapp.view;

import com.example.admin.foursquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foursquareapp.dto_place_info.Place;

import java.util.List;

/**
 * Created by Admin on 22.12.2017.
 */

public interface MainView {
    void initializeComponentToolbar();
    void createListPlace(List<Place> response, List<PlaceInfo> additionalResponse);
    void createNetworkReceiver();
    void sendBroadcast();
}
