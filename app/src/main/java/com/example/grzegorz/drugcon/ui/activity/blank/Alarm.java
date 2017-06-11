package com.example.grzegorz.drugcon.ui.activity.blank;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
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
import java.util.Arrays;



public class Alarm extends MvpActivity implements AlarmView {

    public TimePicker timePicker;
    public static final String TAG = "Alarm";
    // Expandable List View Adapter
    private ExpandableListAdapter exListAdapter;
    // Expandable List View
    private ExpandableListView exListView;
    String my_choice = " ";
    AlarmManager alarmManager;

    String my_drug;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    ArrayList<String> listdrags;
    Button buttonAddAlarm;

    @InjectPresenter
    AlarmPresenter mAlarmPresenter;

    CheckedTextView m, t,w,th, f,s,sun;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Alarm.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

       // exListView = (ExpandableListView) findViewById(R.id.ex_alarmList);
        final DataReader dr = new DataReader(this);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        ArrayList<String> listdrags = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.alarm_list);
        adapter = new ArrayAdapter<String>(Alarm.this, android.R.layout.simple_list_item_1,listdrags);
        m = (CheckedTextView) findViewById(R.id.monday_alarm);
        t = (CheckedTextView) findViewById(R.id.tuesday_alarm);
        w = (CheckedTextView) findViewById(R.id.wednesday_alarm);
        th = (CheckedTextView) findViewById(R.id.thursday_alarm);
        f = (CheckedTextView) findViewById(R.id.friday_alarm);
        s = (CheckedTextView) findViewById(R.id.saturday_alarm);
        sun = (CheckedTextView) findViewById(R.id.sunday_alarm);

        listView.setAdapter(adapter);

        FloatingActionButton button_addAlarm = (FloatingActionButton) findViewById(R.id.button_addAlarm);
        button_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarmFragment(dr);
            }

        });
        }


    public void setAlarmFragment(DataReader dr){

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Alarm.this);
        View mView = getLayoutInflater().inflate(R.layout.fragment_add_alarm,null);
        mBuilder.setTitle("Set your alarm");
        timePicker= (TimePicker) findViewById(R.id.timePicker);
        final Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_drug_list);
        String [] myList = {};
        myList=  mAlarmPresenter.getList(getIntent().getExtras().getString("login"), dr);
        //Toast.makeText(Alarm.this,myList[0],Toast.LENGTH_LONG).show();
        ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(Alarm.this,android.R.layout.simple_spinner_item, myList);

        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinneradapter);
        my_drug = spinner.getSelectedItem().toString();
       /* spinner.setOnItemClickListener(new AdapterView.OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                Object item = parent.getItemAtPosition(pos);

                System.out.println("it works...   ");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        }); //TODO : On spinner change listener
    */


        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hour =0;
                int min =0 ;
               // hour = timePicker.getCurrentHour();
               // min = timePicker.getCurrentMinute();
                my_choice = "Melisa, now";
                my_choice=my_drug +", " + hour +":" +min;

            }
        });

        mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        adapter.add(my_choice);
        adapter.notifyDataSetChanged();
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

    }
}

