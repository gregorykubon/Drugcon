package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.ContactsContract;
import android.text.LoginFilter;
import android.widget.Toast;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.LoginView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.grzegorz.drugcon.ui.activity.blank.Login;

import java.io.IOException;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public static int lol = 69;
    public LoginModel myDb;

    public boolean attemptLogin(String name, String pass, DataReader dr) throws IOException {
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
                    c.close();
                    myDb.close();
                    return true;
                }
            }
        } while (c.moveToNext());
        c.close();
        myDb.close();

        return false;
    }

    public boolean attemptRegister(String name, String pass, DataReader dr) throws IOException {
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

        if(myDb.register(name,pass))return true;

        return false;
    }


}