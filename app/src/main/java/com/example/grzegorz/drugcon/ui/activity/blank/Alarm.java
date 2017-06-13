package com.example.grzegorz.drugcon.ui.activity.blank;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.AlarmView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.AlarmPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TooManyListenersException;


public class Alarm extends MvpActivity implements AlarmView {

    public TimePicker timePicker;
    AlarmManager alarmManager;


    private ListView listView;
    private ArrayAdapter<String> adapter;
    ArrayList<String> listdrags;

    @InjectPresenter
    AlarmPresenter mAlarmPresenter;

    CheckBox m, t, w, th, f, s, sun;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Alarm.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        final DataReader dr = new DataReader(this);
        //Button refresh = (Button) findViewById(R.id.button_refresh);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        mAlarmPresenter = new AlarmPresenter();
        listView = (ListView) findViewById(R.id.alarm_list);
        String [] lista = {};
                lista= mAlarmPresenter.readMyAlarms(getIntent().getExtras().getString("login"),dr);

        adapter = new ArrayAdapter<String>(Alarm.this, android.R.layout.simple_list_item_1, lista);
                //mAlarmPresenter.readMyAlarms(getIntent().getExtras().getString("login"),dr));
        listView.setAdapter(adapter);


        FloatingActionButton button_addAlarm = (FloatingActionButton) findViewById(R.id.button_addAlarm);
        button_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Alarm.this);
                View mView = getLayoutInflater().inflate(R.layout.fragment_add_alarm, null);
                mBuilder.setTitle("Set your alarm");
                final Spinner spinner = (Spinner) mView.findViewById(R.id.medicine_list);
                String[] myList = {};
                timePicker = (TimePicker) mView.findViewById(R.id.timePicker);


                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        //Toast.makeText(Alarm.this, "On change listener", Toast.LENGTH_SHORT).show();
                        mAlarmPresenter.hour = timePicker.getCurrentHour();
                        mAlarmPresenter.min = timePicker.getCurrentMinute();
                    }
                });
                m = (CheckBox) mView.findViewById(R.id.monday_alarm);
                t = (CheckBox) mView.findViewById(R.id.tuesday_alarm);
                w = (CheckBox) mView.findViewById(R.id.wednesday_alarm);
                th = (CheckBox) mView.findViewById(R.id.thursday_alarm);
                f = (CheckBox) mView.findViewById(R.id.friday_alarm);
                s = (CheckBox) mView.findViewById(R.id.saturday_alarm);
                sun = (CheckBox) mView.findViewById(R.id.sunday_alarm);

              /*  t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();
                    }
                });
                w.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();
                    }
                });
                m.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();
                    }
                });
                th.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();
                    }
                });
                f.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();
                    }
                });
                s.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();

                    }
                });
                sun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getCheckedDays();
                    }
                });*/

                myList = mAlarmPresenter.getList(getIntent().getExtras().getString("login"), dr);
                ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(Alarm.this,android.R.layout.simple_spinner_item,myList);
                spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinneradapter);

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String outp = "";

                        if (m.isChecked()){
                            outp = outp.concat(",2");
                        } if(t.isChecked()){
                            outp = outp.concat(",3");
                        } if(w.isChecked()){
                            outp = outp.concat(",4");
                        } if(th.isChecked()){
                            outp = outp.concat(",5");
                        } if(f.isChecked()){
                            outp = outp.concat(",6");
                        } if(s.isChecked()){
                            outp = outp.concat(",7");
                        } if(sun.isChecked()){
                            outp = outp.concat(",1");
                        }
                        mAlarmPresenter.hour = timePicker.getCurrentHour();
                        mAlarmPresenter.min = timePicker.getCurrentMinute();
                        mAlarmPresenter.my_choice=spinner.getSelectedItem().toString();
                        mAlarmPresenter.addAlarm(getApplicationContext(), alarmManager);
                        mAlarmPresenter.putAlarmInDatabase(mAlarmPresenter.my_choice, getIntent().getExtras().getString("login"), outp, mAlarmPresenter.hour, mAlarmPresenter.min, dr);
                        adapter.notifyDataSetChanged();
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

            }

        });

    }

}


