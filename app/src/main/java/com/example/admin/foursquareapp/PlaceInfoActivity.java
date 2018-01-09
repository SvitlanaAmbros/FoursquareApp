package com.example.admin.foursquareapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.foursquareapp.presenter.PlaceInfoPresenter;
import com.example.admin.foursquareapp.presenter.PlaceInfoPresenterImpl;
import com.example.admin.foursquareapp.view.PlaceInfoView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceInfoActivity extends AppCompatActivity implements View.OnClickListener, PlaceInfoView, OnMapReadyCallback{
    private PlaceInfoPresenter placeInfoPresenter;
    private String id;

    @BindView(R.id.img_back_pl)
    ImageView imgBack;

    @BindView(R.id.tv_place_name)
    TextView tvPlaceName;

    @BindView(R.id.tv_place_title)
    TextView tvPlaceNameBlock;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    @BindView(R.id.tv_rate_pl)
    TextView tvRate;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    @BindView(R.id.tv_count_likes)
    TextView tvLikes;

    @BindView(R.id.img_best_photo)
    ImageView bestPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);
        ButterKnife.bind(this);

        imgBack.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        placeInfoPresenter = new PlaceInfoPresenterImpl(this);
        placeInfoPresenter.setInfoAboutPlace(id);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back_pl:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void setPlaceData(Bundle data) {
        tvDescription.setMovementMethod(new ScrollingMovementMethod());

        tvPlaceName.setText(data.getString("title"));
        tvPlaceNameBlock.setText(data.getString("title"));

        tvDescription.setText(data.getString("description"));

        tvRate.setText(String.valueOf(data.getDouble("rate")));

        if ( data.getString("colorRate") != null) {
            tvRate.setBackgroundColor(Color.parseColor("#" + data.getString("colorRate")));
        }
        tvAddress.setText(String.valueOf(data.getString("address")));

        tvLikes.setText(String.valueOf(data.getInt("likes")));

        String imgURL = data.getString("imgpref") +
                "100" +
                data.getString("imgsuf");

        Glide.with(this)
                .load(imgURL)
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.load)
                .error(R.drawable.no_image)
                .into(bestPhoto);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bundle location = placeInfoPresenter.getLocation(id);

        LatLng sydney = new LatLng(location.getDouble("lat"), location.getDouble("lng"));
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
