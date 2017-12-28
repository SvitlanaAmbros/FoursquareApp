package com.example.admin.foresquareapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.foresquareapp.presenter.PlaceInfoPresenter;
import com.example.admin.foresquareapp.presenter.PlaceInfoPresenterImpl;
import com.example.admin.foresquareapp.view.PlaceInfoView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceInfoActivity extends AppCompatActivity implements View.OnClickListener, PlaceInfoView{
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_info);
        ButterKnife.bind(this);

        imgBack.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
        placeInfoPresenter = new PlaceInfoPresenterImpl(this);
        placeInfoPresenter.setInfoAboutPlace(id);
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
        tvPlaceName.setText(data.getString("title"));
        tvPlaceNameBlock.setText(data.getString("title"));

        tvDescription.setText(data.getString("description"));

        tvRate.setText(String.valueOf(data.getDouble("rate")));
        tvRate.setBackgroundColor(Color.parseColor("#" + data.getString("colorRate")));
    }
}
