package com.example.admin.foresquareapp.dto_place_detail;

/**
 * Created by Admin on 26.12.2017.
 */

public class PlaceInfo {
    private String id;
    private Price price;
    private double rating;
    private String ratingColor;
    private String description;
    private Likes likes;
    private BestPhoto bestPhoto;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }
}
