package com.example.admin.foresquareapp.view;

import com.example.admin.foresquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foresquareapp.dto_place_info.Place;

import java.util.List;

/**
 * Created by Admin on 22.12.2017.
 */

public interface MainView {
    void initializaComponentToolbar();
    void createListPlace(List<Place> response, List<PlaceInfo> additionalResponse);
    void createNetworkReceiver();
    void sendBroadcast();
}
