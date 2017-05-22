package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.AlarmView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.AlarmPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

public class Alarm extends MvpActivity implements AlarmView {
    public static final String TAG = "Alarm";
    @InjectPresenter
    AlarmPresenter mAlarmPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Alarm.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Button button_add_alarm= (Button) findViewById(R.id.button_add_alarm);
        button_add_alarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: Start a fragment to set an alarm, in which there will be a drop down list

            }
        });

    }
}
