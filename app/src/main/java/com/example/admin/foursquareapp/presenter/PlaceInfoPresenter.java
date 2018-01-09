package com.example.admin.foursquareapp.presenter;

import android.os.Bundle;

/**
 * Created by Admin on 28.12.2017.
 */

public interface PlaceInfoPresenter {
    void setInfoAboutPlace(String id);
    Bundle getLocation(String id);
}
