package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.ListView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.ListPresenter;
import com.example.grzegorz.drugcon.R;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class List extends MvpActivity implements ListView {
    public static final String TAG = "List";
    @InjectPresenter
    ListPresenter mListPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, List.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}
