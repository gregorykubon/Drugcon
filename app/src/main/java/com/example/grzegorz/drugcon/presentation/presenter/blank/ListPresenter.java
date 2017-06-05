package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.DatabaseModel;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.ListView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.grzegorz.drugcon.ui.activity.blank.List;

import java.io.IOException;
import java.util.Arrays;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {

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


    public String[] getNames(DataReader dr) {
            Cursor c = null;
            DatabaseModel myDb = new DatabaseModel(dr.getContext());
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

            c = myDb.query("Drug",null,null,null,null,null,null);
            String products[] = new String[c.getCount()];
            c.moveToFirst();
            int i=0;
            do{
                products[i]=c.getString(c.getColumnIndex("name"));
                i++;
            }while(c.moveToNext());

            c.close();
            myDb.close();

            return products;


    }

    public void addToList(String login, String drug, String days, DataReader dr) {
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
            //     c = myDb.query("Account", null, null, null, null, null, null);
            try {
                c.moveToFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }

            c = myDb.query("Account", null, null,null, null, null, null);
            c.moveToFirst();
            String toUpdate = null;

            do{
                if (c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                    //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                    toUpdate = c.getString(c.getColumnIndex("list")).toString();
                }
            }while(c.moveToNext());

            toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(","+drug+";"+days).toString();

            ContentValues cv = new ContentValues();
            cv.put("list",toUpdate);

            myDb.getWritableDatabase().update("Account",cv,"login='"+login+"'",null);

            c.close();
            myDb.close();

    }
}
