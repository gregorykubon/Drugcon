package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.database.Cursor;
import android.database.SQLException;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.Change_DeleteView;
import com.example.grzegorz.drugcon.presentation.view.blank.MenuAccView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

@InjectViewState
public class Change_DeletePresenter extends MvpPresenter<Change_DeleteView> {

    public boolean deleteAccount(String name, String pass, DataReader dr) throws IOException {
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
        String[] args = {name, pass};
        c = myDb.query("Account", null, null, null, null, null, null);
        c.moveToFirst();
        do {
            String nam = c.getString(c.getColumnIndex("login"));
            if (nam.equalsIgnoreCase(name)) {
                if (pass.equalsIgnoreCase(c.getString(c.getColumnIndex("password")))) {

                    if (myDb.delete(name)) {


                        c.close();
                        myDb.close();
                        return true;
                    }
                }
            }
        } while (c.moveToNext());
        c.close();
        myDb.close();

        return false;
    }



}



