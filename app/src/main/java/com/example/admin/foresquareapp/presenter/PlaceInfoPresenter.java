package com.example.admin.foresquareapp.presenter;

import android.os.Bundle;

import com.example.admin.foresquareapp.dto_place_info.Location;

/**
 * Created by Admin on 28.12.2017.
 */

public interface PlaceInfoPresenter {
    void setInfoAboutPlace(String id);
    Bundle getLocation(String id);
}
