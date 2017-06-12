package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.res.TypedArrayUtils;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.DatabaseModel;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.SettingsView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.grzegorz.drugcon.presentation.view.blank.Show_ListView;

import java.io.IOException;
import java.util.Arrays;

@InjectViewState
public class Show_ListPresenter extends MvpPresenter<Show_ListView> {


    public String[] getAll(String login,DataReader dr) {
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
                products = c.getString(c.getColumnIndex("history")).substring(c.getString(c.getColumnIndex("history")).indexOf(":")+1).split(":");
            }
        }while(c.moveToNext());

        int i = 1;
        while(i<products.length){
            String added = products[i].substring(products[i].indexOf("Added"));
            String first = products[i].substring(0,products[i].indexOf(";"));
            products[i]=first.concat(" "+added);
            i++;
        }

        String[] yourArray = Arrays.copyOfRange(products, 1, products.length);

        c.close();
        myDb.close();

        return yourArray;


    }






}