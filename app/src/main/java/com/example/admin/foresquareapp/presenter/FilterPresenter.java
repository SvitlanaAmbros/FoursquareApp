package com.example.admin.foresquareapp.presenter;

/**
 * Created by Admin on 26.12.2017.
 */

public interface FilterPresenter {
    void loadStartScreen();
    void updateDefaultBtnSortBy(int id);
    int getValueSortBy();
    void resetFilter();
}
