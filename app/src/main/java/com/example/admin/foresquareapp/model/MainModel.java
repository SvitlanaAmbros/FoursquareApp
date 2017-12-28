package com.example.admin.foresquareapp.model;

import com.example.admin.foresquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foresquareapp.dto_place_info.Place;

import java.util.List;

/**
 * Created by Admin on 22.12.2017.
 */

public interface MainModel {
    List<PlaceInfo> getAdditionalPlaceInfoList();
    List<Place> getPlaceInfoList();
    void setPlaceInfoList(List<Place> placeInfoList);
    int getDefaultBtnSortBy();
    void setDefaultBtnSortBy(int defaultBtnSortBy);
}
