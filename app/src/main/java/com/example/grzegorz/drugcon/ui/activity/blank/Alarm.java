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


public class Alarm extends MvpActivity implements AlarmView {

    public TimePicker timePicker;
       AlarmManager alarmManager;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    ArrayList<String> listdrags;

    @InjectPresenter
    AlarmPresenter mAlarmPresenter;

    CheckBox m, t,w,th, f,s,sun;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Alarm.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        final DataReader dr = new DataReader(this);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        ArrayList<String> listdrags = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.alarm_list);
        adapter = new ArrayAdapter<String>(Alarm.this, android.R.layout.simple_list_item_1,listdrags);
        listView.setAdapter(adapter);

        FloatingActionButton button_addAlarm = (FloatingActionButton) findViewById(R.id.button_addAlarm);
        button_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlarmPresenter.hour = 6;
                mAlarmPresenter.min = 39;

                mAlarmPresenter.addAlarm(mAlarmPresenter.hour, mAlarmPresenter.min, Alarm.this, alarmManager);
                Toast.makeText(Alarm.this, mAlarmPresenter.calendar.toString(), Toast.LENGTH_SHORT).show();
                //setAlarmFragment(dr);
               // setAl(dr);
                //mAlarmPresenter.my_choice = mAlarmPresenter.my_choice + " " + mAlarmPresenter.hour +" : " + mAlarmPresenter.min;
               // adapter.add(mAlarmPresenter.my_choice);
               // adapter.notifyDataSetChanged();
            }

        });
        }

    public void setAl(DataReader dr){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Alarm.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_add_alarm,null);
        mBuilder.setTitle("Set your alarm");
        final Spinner spinner = (Spinner) mView.findViewById(R.id.medicine_list);
        String [] myList = {};
        timePicker = (TimePicker) mView.findViewById(R.id.timePicker);
       /* final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute= c.get(Calendar.MINUTE);
        timePicker.setCurrentHour(hour);                      // Set time.
        timePicker.setCurrentMinute(minute); */

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(Alarm.this, "On change listener", Toast.LENGTH_SHORT).show();
                mAlarmPresenter.hour = timePicker.getCurrentHour();
                mAlarmPresenter.min = timePicker.getCurrentMinute();
            }
        });
        m = (CheckBox) findViewById(R.id.monday_alarm);
        t = (CheckBox) findViewById(R.id.tuesday_alarm);
        w = (CheckBox) findViewById(R.id.wednesday_alarm);
        th = (CheckBox) findViewById(R.id.thursday_alarm);
        f = (CheckBox) findViewById(R.id.friday_alarm);
        s = (CheckBox) findViewById(R.id.saturday_alarm);
        sun = (CheckBox) findViewById(R.id.sunday_alarm);
        myList=  mAlarmPresenter.getList(getIntent().getExtras().getString("login"), dr);
        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(Alarm.this,android.R.layout.simple_spinner_item, myList);
        spinner.setAdapter(spinneradapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAlarmPresenter.my_choice = (String) spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mAlarmPresenter.my_choice = spinner.getSelectedItem().toString();
            }
        });


        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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

}

