package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.util.Calendar;


import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.Notification_receiver;
import com.example.grzegorz.drugcon.presentation.view.blank.AlarmView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@InjectViewState
public class AlarmPresenter extends MvpPresenter<AlarmView> {

    ArrayList<String> alarms;
    public String my_choice= "";
    public int hour, min;
    AlarmManager alarmManager;
    public Calendar calendar;


    public String[] getList(String login,DataReader dr) {
        Cursor c = null;
        LoginModel myDb = new LoginModel(dr.getContext());
        try {
            myDb.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDb.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        c = myDb.query("Account",null,null,null,null,null,null);
        String products[] = new String[c.getCount()];
        c.moveToFirst();
        do{
            if(c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                products = c.getString(c.getColumnIndex("list")).substring(c.getString(c.getColumnIndex("list")).indexOf(",")+1).split(",");
            }
        }while(c.moveToNext());

        int i = 1;
        while(i<products.length){
            products[i]=products[i].substring(0,products[i].indexOf(";"));
            i++;
        }

        String[] yourArray = Arrays.copyOfRange(products, 1, products.length);

        c.close();
        myDb.close();

        return yourArray;

    }
    public void setCalendar( int hour, int min){

        calendar = Calendar.getInstance();
        //calendar.set(Calendar.HOUR_OF_DAY, hour);
       calendar.set(Calendar.MINUTE, min);
       // calendar.set(Calendar.SECOND);

    }

    public void addAlarm(int hour, int min, Context context, AlarmManager alarmManager){

        setCalendar(hour, min);
        Intent intent = new Intent (context, Notification_receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent );
    }
    public void removeAlarm(String alarm){


    }

}
