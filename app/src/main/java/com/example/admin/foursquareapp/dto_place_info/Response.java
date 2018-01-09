package com.example.admin.foursquareapp.dto_place_info;

import java.util.List;

/**
 * Created by Admin on 26.12.2017.
 */

public class Response {
    private List<Place> venues;

    public List<Place> getVenues() {
        return venues;
    }

    public void setVenues(List<Place> venues) {
        this.venues = venues;
    }
}
