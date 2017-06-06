package com.example.grzegorz.drugcon.ui.activity.blank;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    public static final String TAG = "Alarm";
    // Expandable List View Adapter
    private ExpandableListAdapter exListAdapter;
    // Expandable List View
    private ExpandableListView exListView;

    private ListView listView;
    private ArrayAdapter<String> adapter;
    ArrayList<String> listdrags;
    Button buttonAddAlarm;

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

       // exListView = (ExpandableListView) findViewById(R.id.ex_alarmList);

        final DataReader dr = new DataReader(this);


        ArrayList<String> listdrags = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.alarm_list);

         adapter = new ArrayAdapter<String>(Alarm.this, android.R.layout.simple_list_item_1,listdrags);
        //adapter = new ArrayAdapter<String>(this, R.layout.content_list, R.id.product_name);

        listView.setAdapter(adapter);

        FloatingActionButton button_addAlarm = (FloatingActionButton) findViewById(R.id.button_addAlarm);
        button_addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnclick(dr);
            }

        });



        }

        public void onBtnclick(DataReader dr) {


            AlertDialog.Builder mBuilder = new AlertDialog.Builder(Alarm.this);
            View mView = getLayoutInflater().inflate(R.layout.fragment_add_alarm,null);
            mBuilder.setTitle("Set your alarm");
            final TimePicker timePicker= (TimePicker) findViewById(R.id.timePicker);
            final Spinner spinner = (Spinner) mView.findViewById(R.id.spinner_drug_list);
            String [] myList = {};
            //myList=  mAlarmPresenter.getList(getIntent().getExtras().getString("login"), dr); //uncaught exception java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String)' on a null object reference
            //Toast.makeText(Alarm.this,myList[0],Toast.LENGTH_LONG).show();
            ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(Alarm.this,android.R.layout.simple_spinner_item, myList);

            spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinneradapter);

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


            adapter.add("Cokolwiek");
            adapter.notifyDataSetChanged();

        }


}

