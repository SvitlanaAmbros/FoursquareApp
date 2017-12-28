package com.example.admin.foresquareapp.presenter;

import android.os.Bundle;

import com.example.admin.foresquareapp.App;
import com.example.admin.foresquareapp.view.PlaceInfoView;

/**
 * Created by Admin on 28.12.2017.
 */

public class PlaceInfoPresenterImpl implements PlaceInfoPresenter {
    private PlaceInfoView placeInfoView;

    public PlaceInfoPresenterImpl(PlaceInfoView placeInfoView) {
        this.placeInfoView = placeInfoView;
    }

    @Override
    public void setInfoAboutPlace(String id) {
        String title = "";
        int ind = 0;
        for (int i = 0; i < App.getInstance().getMainModel().getPlaceInfoList().size(); i++) {
            if(App.getInstance().getMainModel().getPlaceInfoList().get(i).getId().equals(id)){
                title = App.getInstance().getMainModel().getPlaceInfoList().get(i).getName();
                ind = i;
                break;
            }
        }

        Bundle item = new Bundle();
        item.putString("title", title);
        item.putString("description", App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).getDescription());
        placeInfoView.setPlaceData(item);
    }
}
