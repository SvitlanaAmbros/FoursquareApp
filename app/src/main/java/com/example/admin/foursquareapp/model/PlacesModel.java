package com.example.admin.foursquareapp.model;

import com.example.admin.foursquareapp.R;
import com.example.admin.foursquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foursquareapp.dto_place_info.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 26.12.2017.
 */

public class PlacesModel implements MainModel {
    private List<PlaceInfo> additionalPlaceInfoList;
    private List<Place> placeInfoList;
    private int defaultBtnSortBy = R.id.btn_rel;

    public PlacesModel() {
        this.additionalPlaceInfoList = new ArrayList<>();
        this.placeInfoList = new ArrayList<>();
    }

    public List<PlaceInfo> getAdditionalPlaceInfoList() {
        return additionalPlaceInfoList;
    }

    public void setAdditionalPlaceInfoList(List<PlaceInfo> additionalPlaceInfoList) {
        this.additionalPlaceInfoList = additionalPlaceInfoList;
    }

    public List<Place> getPlaceInfoList() {
        return placeInfoList;
    }

    public void setPlaceInfoList(List<Place> placeInfoList) {
        this.placeInfoList = placeInfoList;
    }

    public int getDefaultBtnSortBy() {
        return defaultBtnSortBy;
    }

    public void setDefaultBtnSortBy(int defaultBtnSortBy) {
        this.defaultBtnSortBy = defaultBtnSortBy;
    }
}
