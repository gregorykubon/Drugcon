package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.SettingsView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.SettingsPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class Settings extends MvpActivity implements SettingsView {
    public static final String TAG = "Settings";
    @InjectPresenter
    SettingsPresenter mSettingsPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Settings.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

}
