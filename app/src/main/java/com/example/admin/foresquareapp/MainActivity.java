package com.example.admin.foresquareapp;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.admin.foresquareapp.adapters.RVPlaceAdapter;
import com.example.admin.foresquareapp.dto_place_detail.PlaceInfo;
import com.example.admin.foresquareapp.dto_place_info.Place;
import com.example.admin.foresquareapp.interfaces.FindConnectionListener;
import com.example.admin.foresquareapp.interfaces.OnItemClickListener;
import com.example.admin.foresquareapp.presenter.MainPresenter;
import com.example.admin.foresquareapp.view.MainView;
import com.example.admin.foresquareapp.presenter.MainPresenterImpl;
import com.example.admin.foresquareapp.receiver.NetworkReceiver;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener, FindConnectionListener,
        OnItemClickListener{
    private MainPresenter mainPresenter;

    private LocationManager locationManager;
    static final int REQUEST_LOCATION = 1;
    private NetworkReceiver networkReceiver;
    private Location location;

    @BindView(R.id.toolbarMain)
    Toolbar toolbar;

    @BindView(R.id.cancel)
    ImageView cancel;

    @BindView(R.id.search_text)
    EditText searchText;

    @BindView(R.id.search)
    ImageView search;

    @BindView(R.id.filter)
    ImageView filter;

    @BindView(R.id.rvPlace)
    RecyclerView rvPlace;

    @BindView(R.id.view_flipper_main_activity)
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewFlipper.setDisplayedChild(0);

        initializaComponentToolbar();
        createNetworkReceiver();

        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        mainPresenter = new MainPresenterImpl(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                searchText.setText("");
                break;
            case R.id.filter:
                Intent intent = new Intent(this, FilterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.search:
                viewFlipper.setDisplayedChild(3);
                sendBroadcast();

                location = findCurrentCoordinates();

                break;
        }
    }

    public void sendBroadcast() {
        Intent intent = new Intent();
        intent.setAction(App.getInstance().BROADCAST_ACTION);
        sendBroadcast(intent);
    }

    @Override
    public void initializaComponentToolbar() {
        setSupportActionBar(toolbar);
        cancel.setOnClickListener(this);
        search.setOnClickListener(this);
        filter.setOnClickListener(this);
    }

    @Override
    public void createListPlace(List<Place> response, List<PlaceInfo> additionalResponse) {
        viewFlipper.setDisplayedChild(1);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) rvPlace.getLayoutParams();
        lp.setMargins(0,toolbar.getHeight(),0,0);
        rvPlace.setLayoutParams(lp);

        rvPlace.setLayoutManager(new LinearLayoutManager(this));
        RVPlaceAdapter rvPlaceAdapter = new RVPlaceAdapter(response, additionalResponse, this);
        rvPlace.setAdapter(rvPlaceAdapter);

    }

    @Override
    public void createNetworkReceiver() {
        networkReceiver = new NetworkReceiver(this);

        IntentFilter intentFilter = new IntentFilter(App.getInstance().BROADCAST_ACTION);
        registerReceiver(networkReceiver, intentFilter);
    }

    public Location findCurrentCoordinates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, REQUEST_LOCATION);
            return null;
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null){
               return location;

            }
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_LOCATION:
                findCurrentCoordinates();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {return;}
        int btnSort = data.getIntExtra("sort", R.id.btn_rel);

        mainPresenter.updateRV(btnSort);
    }

    @Override
    public void connectionFind() {
        if(location != null){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(location.getLatitude()).append(",").append(location.getLongitude());

            mainPresenter.searchInfo(stringBuilder.toString(), searchText.getText().toString());
        }
    }

    @Override
    public void noConnection() {
        viewFlipper.setDisplayedChild(2);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, PlaceInfoActivity.class);
        intent.putExtra("id", App.getInstance().getMainModel().getPlaceInfoList().get(position).getId());
        startActivity(intent);
    }
}
