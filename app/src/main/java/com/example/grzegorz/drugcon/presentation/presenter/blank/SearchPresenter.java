package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.database.Cursor;
import android.database.SQLException;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.DatabaseModel;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.SearchView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

@InjectViewState
public class SearchPresenter extends MvpPresenter<SearchView> {

    public String[] getAll(DataReader dr) {
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

        c = myDb.query("drug",null,null,null,null,null,null);
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


}
