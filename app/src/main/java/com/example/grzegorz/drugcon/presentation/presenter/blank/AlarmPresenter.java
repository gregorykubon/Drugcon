package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.text.SimpleDateFormat;
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
import java.util.Random;

@InjectViewState
public class AlarmPresenter extends MvpPresenter<AlarmView> {

    ArrayList<String> alarms;
    public String my_choice= "";
    public int hour=0;
    public int min= 0;

    public Calendar calendar;
    public String daysString =" ";


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

    public void setCalendar(int dayOfWeek){

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);


    }
    public void alarmCreator( int dayOfWeek, Context context, AlarmManager alarmManager){

        setCalendar(dayOfWeek);
        Intent intent = new Intent (context, Notification_receiver.class);
        Random r = new Random();
        int randomInt = r.nextInt(10000);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, randomInt, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), (AlarmManager.INTERVAL_DAY * 7) , pendingIntent );

    }

    public void addAlarm(Context context, AlarmManager alarmManager){

        if ( daysString == null ) daysString = "1,3";
        String [] days_numbers_str = daysString.split(",");
        int[] days_numbers = new int[days_numbers_str.length];
        for(int i = 0;i < days_numbers_str.length;i++)
        {
            days_numbers[i] = (int) Integer.parseInt(days_numbers_str[i]);
        }
        for (int i =0 ; i < days_numbers.length ; i ++){
            alarmCreator( days_numbers[i], context, alarmManager);
        }
    }


    public String[] getAlarmDaysList(String login,DataReader dr) {
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

        c = myDb.query("Alarms",null,null,null,null,null,null);
        String days[] = new String[c.getCount()];
        c.moveToFirst();
        do{
            if(c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                days = c.getString(c.getColumnIndex("days")).substring(c.getString(c.getColumnIndex("days")).indexOf(",")+1).split(",");
            }
        }while(c.moveToNext());

        int i = 1;
        while(i<days.length){
            days[i]=days[i].substring(0,days[i].indexOf(";"));
            i++;
        }

        String[] yourArray = Arrays.copyOfRange(days, 1, days.length);

        c.close();
        myDb.close();

        return yourArray;

    }

        public void putAlarmInDatabase(String drug,String login, String days, int hour, int min, DataReader dr) {
           // Cursor c = null;
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
            try {
              //  c.moveToFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ContentValues cv = new ContentValues();
            cv.put("days",days);
            cv.put("hour",hour);
            cv.put("minute",min);
            cv.put("login",login);
            cv.put("drug_name",drug);
            //long a = myDb.getWritableDatabase().insert("Alarms",null,cv);

            myDb.getWritableDatabase().insert("Alarms",null,cv);

           // c.close();
            myDb.close();
        }

    public String [] readMyAlarms(String login,DataReader dr){

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
        c = myDb.query("Alarms", null, null, null, null, null, null);
        try {
            c.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i=0;
        do{
            if (c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                i++;
            }
        }while(c.moveToNext());
        String[] alarms = new String[i+1];
        i=0;
        c.moveToFirst();
        do{
            if (c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                alarms[i]="Days:";
                alarms[i]=alarms[i].concat(c.getString(c.getColumnIndex("days")));
                alarms[i]=alarms[i].concat("Hour:");
                alarms[i]=alarms[i].concat(c.getString(c.getColumnIndex("hour")));
                alarms[i]=alarms[i].concat("Minute:");
                alarms[i]=alarms[i].concat(c.getString(c.getColumnIndex("minute")));
                alarms[i]=alarms[i].concat("DrugName:");
                alarms[i]=alarms[i].concat(c.getString(c.getColumnIndex("drug_name")));
                i++;
            }
        }while(c.moveToNext());

        return alarms;
    }

}
