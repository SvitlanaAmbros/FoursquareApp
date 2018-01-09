package com.example.admin.foursquareapp.dto_place_info;

import java.util.List;

/**
 * Created by Admin on 25.12.2017.
 */

public class Place {
    private String id;
    private String name;
    private List<Categories> categories;
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
