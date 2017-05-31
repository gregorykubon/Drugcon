package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.database.Cursor;
import android.database.SQLException;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.DatabaseModel;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.ListView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

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

        String[] yourArray = Arrays.copyOfRange(products, 1, products.length);

        c.close();
        myDb.close();

        return yourArray;

    }


}
