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
        item.putDouble("rate", App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).getRating());
        item.putString("colorRate", App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).getRatingColor());
        item.putString("address", App.getInstance().getMainModel().getPlaceInfoList().get(ind).getLocation().getAddress());
        item.putInt("likes", App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).getLikes().getCount());

        if(App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).
                getBestPhoto().getPrefix() != null && App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).
                getBestPhoto().getSuffix() != null) {
            item.putString("imgpref", App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).
                    getBestPhoto().getPrefix());
            item.putString("imgsuf", App.getInstance().getMainModel().getAdditionalPlaceInfoList().get(ind).
                    getBestPhoto().getSuffix());
        }
        placeInfoView.setPlaceData(item);
    }

    @Override
    public Bundle getLocation(String id) {
        Bundle bundle = new Bundle();

        for (int i = 0; i < App.getInstance().getMainModel().getPlaceInfoList().size(); i++) {
            if(App.getInstance().getMainModel().getPlaceInfoList().get(i).getId().equals(id)){
                bundle.putDouble("lat", App.getInstance().getMainModel().getPlaceInfoList().get(i).getLocation().getLat());
                bundle.putDouble("lng", App.getInstance().getMainModel().getPlaceInfoList().get(i).getLocation().getLng());
                break;
            }
        }
        return bundle;
    }
}
