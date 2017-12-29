package com.example.admin.foresquareapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.foresquareapp.presenter.FilterPresenter;
import com.example.admin.foresquareapp.view.FilterView;
import com.example.admin.foresquareapp.presenter.FilterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, FilterView{

    private FilterPresenter filterPresenter;

    @BindView(R.id.img_back)
    ImageView imgBack;

    @BindView(R.id.tv_reset)
    TextView tvReset;

    @BindView(R.id.btn_rel)
    Button btnRel;

    @BindView(R.id.btn_dist)
    Button btnDist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        imgBack.setOnClickListener(this);
        tvReset.setOnClickListener(this);
        btnDist.setOnClickListener(this);
        btnRel.setOnClickListener(this);

        filterPresenter = new FilterPresenterImpl(this);
        filterPresenter.loadStartScreen();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("sort", filterPresenter.getValueSortBy());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
                Intent intent = new Intent();
                intent.putExtra("sort", filterPresenter.getValueSortBy());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_reset:
                filterPresenter.resetFilter();
                break;
            case R.id.btn_rel:
                filterPresenter.updateDefaultBtnSortBy(R.id.btn_rel);

                updateSortButton(R.id.btn_rel,R.id.btn_dist);

                break;
            case R.id.btn_dist:
                filterPresenter.updateDefaultBtnSortBy(R.id.btn_dist);

                updateSortButton(R.id.btn_dist,R.id.btn_rel);
            default:
                break;
        }
    }

    @Override
    public void updateSortButton(int idChoosed,int idNotChoosed) {
        Button btnChoosed = findViewById(idChoosed);
        btnChoosed.setBackgroundColor(getColor(R.color.colorAccentLight));

        Button btnNotChoosed = findViewById(idNotChoosed);
        btnNotChoosed.setBackgroundColor(getColor(R.color.icons));

    }

}
