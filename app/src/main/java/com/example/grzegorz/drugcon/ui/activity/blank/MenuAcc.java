package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.MenuAccView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.MenuAccPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class MenuAcc extends MvpActivity implements MenuAccView {
    public static final String TAG = "MenuAcc";
    @InjectPresenter
    MenuAccPresenter mMenuAccPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, MenuAcc.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_acc);
        setContentView(R.layout.activity_menu_acc);
    }
}
