package com.example.admin.foresquareapp.presenter;

import com.example.admin.foresquareapp.App;
import com.example.admin.foresquareapp.R;
import com.example.admin.foresquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foresquareapp.dto_place_detail.ResponceAddedInfoPlace;
import com.example.admin.foresquareapp.dto_place_info.Place;
import com.example.admin.foresquareapp.dto_place_info.ResponseByQuery;
import com.example.admin.foresquareapp.interfaces.AddInfoPlaceLoaderListener;
import com.example.admin.foresquareapp.interfaces.MainInfoPlaceLoadListener;
import com.example.admin.foresquareapp.view.MainView;
import com.example.admin.foresquareapp.interfaces.ServerResponseListener;
import com.example.admin.foresquareapp.model.PlacesModel;
import com.example.admin.foresquareapp.server.ServerRequestBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Admin on 22.12.2017.
 */

public class MainPresenterImpl implements MainPresenter, ServerResponseListener, MainInfoPlaceLoadListener, AddInfoPlaceLoaderListener {
    private MainView mainView;
    private ServerRequestBuilder serverRequestBuilder;
    private int position = 0;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        serverRequestBuilder = new ServerRequestBuilder(this, this,this);
    }

    @Override
    public void searchInfo(String coord, String query) {
        App.getInstance().setMainModel(new PlacesModel());
        position = 0;
        serverRequestBuilder.searchByQuery(coord, query);
    }

    @Override
    public void updateRV(int id) {
        if(id == R.id.btn_rel){
            mainView.createListPlace(App.getInstance().getMainModel().getPlaceInfoList(),
                    App.getInstance().getMainModel().getAdditionalPlaceInfoList());
        }else{
            List<Place> sortedListPlace = sortListByDistance(App.getInstance().getMainModel().getPlaceInfoList());

            List<PlaceInfo> sortedListAdditionalInfo = sortListAdditionalInfo(sortedListPlace,
                    App.getInstance().getMainModel().getAdditionalPlaceInfoList());

            mainView.createListPlace(sortedListPlace, sortedListAdditionalInfo);
        }
    }

    @Override
    public <T> void successfulRequest(T response) {

    }

    @Override
    public void errorRequest() {

    }

    @Override
    public <T> void infoLoaded(T response) {
        ResponseByQuery responseByQuery = (ResponseByQuery) response;
        App.getInstance().getMainModel().setPlaceInfoList(responseByQuery.getResponse().getVenues());
        for (int i = 0; i < App.getInstance().getMainModel().getPlaceInfoList().size(); i++) {
            serverRequestBuilder.searchAddInfoPlace(App.getInstance().getMainModel().getPlaceInfoList().get(i).getId());
        }
    }

    @Override
    public <T> void additionalInfoLoaded(T response) {
        position++;
        ResponceAddedInfoPlace responceAddedInfoPlace = (ResponceAddedInfoPlace) response;
        App.getInstance().getMainModel().getAdditionalPlaceInfoList().add(responceAddedInfoPlace.getResponse().getVenue());

        if(position == App.getInstance().getMainModel().getPlaceInfoList().size() - 1) {
            if(App.getInstance().getMainModel().getDefaultBtnSortBy() == R.id.btn_rel){
                mainView.createListPlace(App.getInstance().getMainModel().getPlaceInfoList(),
                        App.getInstance().getMainModel().getAdditionalPlaceInfoList());
            }else{
                List<Place> sortedListPlace = sortListByDistance(App.getInstance().getMainModel().getPlaceInfoList());

                List<PlaceInfo> sortedListAdditionalInfo = sortListAdditionalInfo(sortedListPlace,
                        App.getInstance().getMainModel().getAdditionalPlaceInfoList());

                mainView.createListPlace(sortedListPlace, sortedListAdditionalInfo);
            }
        }
    }

    public List<Place> sortListByDistance(List<Place> placeInfoList) {
        int[] number = new int[placeInfoList.size()];
        List<Place> placeInfoListForSorting = new ArrayList<>();
        placeInfoListForSorting.addAll(placeInfoList);

        for (int i = 0; i < placeInfoList.size(); i++) {
            number[i] = i;
        }

        Collections.sort(placeInfoListForSorting, new Comparator<Place>() {
            @Override
            public int compare(Place pInfo, Place nInfo) {
                return pInfo.getLocation().getDistance() > nInfo.getLocation().getDistance() ? 1 :
                        (pInfo.getLocation().getDistance() < nInfo.getLocation().getDistance()) ? -1 : 0;
            }
        });

        return placeInfoListForSorting;
    }

    public List<PlaceInfo> sortListAdditionalInfo(List<Place> placeInfoList,List<PlaceInfo> additionalPlaceInfoList){
        List<PlaceInfo> additionalPlaceInfoListForSorting = new ArrayList<>();

        for (int i = 0; i < placeInfoList.size(); i++) {
            additionalPlaceInfoListForSorting.add(findPlaceById(placeInfoList.get(i).getId(), additionalPlaceInfoList));
        }

        return additionalPlaceInfoList;
    }

    public PlaceInfo findPlaceById(String id, List<PlaceInfo> additionalPlaceInfoList){
        PlaceInfo placeInfo = new PlaceInfo();

        for (int i = 0; i < additionalPlaceInfoList.size(); i++) {
            if(additionalPlaceInfoList.get(i).getId() == id){
                placeInfo = additionalPlaceInfoList.get(i);
                break;
            }
        }

        return placeInfo;
    }

//    @Override
//    public void changeSortingByChanged() {
//        if(App.getInstance().getMainModel().getDefaultBtnSortBy() == R.id.btn_rel){
//            mainView.createListPlace(App.getInstance().getMainModel().getPlaceInfoList(),
//                    App.getInstance().getMainModel().getAdditionalPlaceInfoList());
//        }else{
//            List<Place> sortedListPlace = sortListByDistance(App.getInstance().getMainModel().getPlaceInfoList());
//
//            List<PlaceInfo> sortedListAdditionalInfo = sortListAdditionalInfo(sortedListPlace,
//                    App.getInstance().getMainModel().getAdditionalPlaceInfoList());
//
//            mainView.createListPlace(sortedListPlace, sortedListAdditionalInfo);
//        }
//    }


}
