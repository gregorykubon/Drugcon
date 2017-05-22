package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.SearchView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.SearchPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class Search extends MvpActivity implements SearchView {
    public static final String TAG = "Search";
    @InjectPresenter
    SearchPresenter mSearchPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Search.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}
