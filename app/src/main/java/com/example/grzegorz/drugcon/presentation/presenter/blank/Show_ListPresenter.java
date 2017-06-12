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

@InjectViewState
public class Show_ListPresenter extends MvpPresenter<Show_ListView> {


    public String[] getAll(DataReader dr) {
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
        int i=0;
        do{
            products[i]=(c.getString(c.getColumnIndex("login"))+": "+c.getString(c.getColumnIndex("history"))).replace(",", " ");
            i++;
        }while(c.moveToNext());



        c.close();
        myDb.close();

        return products;

    }






}