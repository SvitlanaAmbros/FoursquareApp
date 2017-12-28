package com.example.admin.foresquareapp.presenter;

import com.example.admin.foresquareapp.App;
import com.example.admin.foresquareapp.R;
import com.example.admin.foresquareapp.view.FilterView;

/**
 * Created by Admin on 26.12.2017.
 */

public class FilterPresenterImpl implements FilterPresenter {
    private FilterView filterView;

    public FilterPresenterImpl(FilterView filterView) {
        this.filterView = filterView;
    }

    @Override
    public void loadStartScreen() {
        if(App.getInstance().getMainModel().getDefaultBtnSortBy() == R.id.btn_rel) {
            filterView.updateSortButton(R.id.btn_rel, R.id.btn_dist);
        }else {
            filterView.updateSortButton(R.id.btn_dist, R.id.btn_rel);
        }
    }

    @Override
    public void updateDefaultBtnSortBy(int id) {
        App.getInstance().getMainModel().setDefaultBtnSortBy(id);
    }

    @Override
    public int getValueSortBy() {
        return App.getInstance().getMainModel().getDefaultBtnSortBy();
    }

    @Override
    public void resetFilter() {
        App.getInstance().getMainModel().setDefaultBtnSortBy(R.id.btn_rel);
        filterView.updateSortButton(R.id.btn_rel, R.id.btn_dist);
    }
}
