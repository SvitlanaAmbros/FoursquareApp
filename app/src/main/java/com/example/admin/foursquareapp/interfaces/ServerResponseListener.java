package com.example.admin.foursquareapp.interfaces;

/**
 * Created by Admin on 25.12.2017.
 */

public interface ServerResponseListener {
    <T> void successfulRequest(T response);
    void errorRequest();
}
