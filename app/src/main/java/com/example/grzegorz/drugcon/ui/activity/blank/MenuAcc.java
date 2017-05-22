package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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


        ImageButton button_list = (ImageButton) findViewById(R.id.button_list);
        button_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), List.class);
                startActivity(intent);

            }
        });
        ImageButton button_alarm = (ImageButton) findViewById(R.id.button_alarm);

        button_alarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alarm.class);
                startActivity(intent);

            }
        });
        ImageButton button_search = (ImageButton) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                startActivity(intent);

            }
        });

        ImageButton button_settings = (ImageButton) findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);

            }
        });
    }
}
